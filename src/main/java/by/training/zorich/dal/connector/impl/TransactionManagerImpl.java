/**
 * TransactionManager is adapter for MySqlDBConnector.
 * Give transaction option.
 * In this case connection saved in ThreadLocal variable before transaction finished.
 * Than connection give back to pool.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.dal.connector.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.connector.TransactionManagerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class TransactionManagerImpl implements TransactionManager {
    private static final ThreadLocal<TransactionData> TRANSACTION_DATA_THREAD_LOCAL = new ThreadLocal<>();

    private DataSourceConnector dataSourceConnector;

    private TransactionManagerImpl() {
    }

    private static class TransactionManagerImplHelper {
        private static final TransactionManagerImpl TRANSACTION_MANAGER = new TransactionManagerImpl();
    }

    public static TransactionManagerImpl getInstance() {
        return TransactionManagerImplHelper.TRANSACTION_MANAGER;
    }

    public void init(DataSourceConnector dataSourceConnector) {
        this.dataSourceConnector = dataSourceConnector;
    }

    public Connection getConnectionForTransaction() throws TransactionManagerException {
        Connection connection = null;

        try {
            if (TRANSACTION_DATA_THREAD_LOCAL.get() == null) {
                connection = dataSourceConnector.getConnection();

                connection.setAutoCommit(false);

                TransactionData transactionData = new TransactionData();
                transactionData.setConnection(connection);

                TRANSACTION_DATA_THREAD_LOCAL.set(transactionData);
            } else {
                connection = TRANSACTION_DATA_THREAD_LOCAL.get().getConnection();
            }
        } catch (SQLException e) {
            throw new TransactionManagerException("Turning on of transaction is failed!", e);
        } catch (DataSourceConnectorException e) {
            throw new TransactionManagerException("Getting connection from pool is failed!", e);
        }

        return connection;
    }

    @Override
    public PreparedStatement getCachedPreparedStatementForTransaction(String queryPattern,
                                                                      TransactionDAOOperationType transactionDAOOperationType) throws
                                                                                                                               TransactionManagerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        TransactionData transactionData = TRANSACTION_DATA_THREAD_LOCAL.get();

        if (transactionData != null) {
            try {
                preparedStatement = transactionData.getActualPreparedStatement(transactionDAOOperationType);

                if (preparedStatement == null) {
                    connection = transactionData.getConnection();
                    preparedStatement = connection.prepareStatement(queryPattern);
                    transactionData.setPreparedStatement(transactionDAOOperationType, preparedStatement);
                }
            } catch (SQLException e) {
                throw new TransactionManagerException("Creating of preparedStatement for transaction is failed!", e);
            }
        }

        return preparedStatement;
    }

    public void giveBackTransactionConnection() throws TransactionManagerException {
        try {
            TransactionData transactionData = TRANSACTION_DATA_THREAD_LOCAL.get();
            if (transactionData != null) {
                Connection connection = transactionData.getConnection();

                if (connection != null) {
                    connection.commit();
                    connection.setAutoCommit(true);

                    closePreparedStatements();

                    TRANSACTION_DATA_THREAD_LOCAL.remove();
                    dataSourceConnector.giveBackConnection(connection);
                }
            }
        } catch (SQLException e) {
            throw new TransactionManagerException("Turning off transaction is failed!", e);
        } catch (DataSourceConnectorException e) {
            throw new TransactionManagerException("Giving back connection is failed!", e);
        }


    }

    public void giveBackEmergenclyTransactionConnection() throws TransactionManagerException {
        try {
            TransactionData transactionData = TRANSACTION_DATA_THREAD_LOCAL.get();

            if (transactionData != null) {
                Connection connection = transactionData.getConnection();

                if (connection != null) {
                    connection.rollback();
                    connection.setAutoCommit(true);

                    closePreparedStatements();

                    TRANSACTION_DATA_THREAD_LOCAL.remove();
                    dataSourceConnector.giveBackConnection(connection);
                }
            }
        } catch (SQLException e) {
            throw new TransactionManagerException("Turning off of transaction is failed!", e);
        } catch (DataSourceConnectorException e) {
            throw new TransactionManagerException("Giving back connection is failed!", e);
        }
    }

    private void closePreparedStatements() throws SQLException {
        TransactionData transactionData = TRANSACTION_DATA_THREAD_LOCAL.get();

        if (transactionData != null) {
            List<PreparedStatement> preparedStatements = transactionData.getAllPreparedStatements();

            for (PreparedStatement preparedStatement : preparedStatements) {
                preparedStatement.close();
            }
        }
    }
}
