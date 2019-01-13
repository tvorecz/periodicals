/**
 * CommonDAO includes common functions for selecting and updating data-base-information.
 * It creates for using concrete DAO-classes.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.dal.dao.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.connector.TransactionManagerException;
import by.training.zorich.dal.connector.impl.TransactionDAOOperationType;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonDAO<T> {
    private final DataSourceConnector connector; //provide connection from pool
    private final TransactionManager transactionManager; //provide working with connection pool and transations
    private final SQLExecutor sqlExecutor; //execute query to data source and
    private final ResultHandlerRepository resultHandlerRepository; //provide handler for handle resultset

    public CommonDAO(DataSourceConnector connector,
                     TransactionManager transactionManager,
                     SQLExecutor sqlExecutor,
                     ResultHandlerRepository resultHandlerRepository) {
        this.connector = connector;
        this.transactionManager = transactionManager;
        this.sqlExecutor = sqlExecutor;
        this.resultHandlerRepository = resultHandlerRepository;
    }

    protected DataSourceConnector getConnector() {
        return connector;
    }

    protected SQLExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    protected ResultHandlerRepository getResultHandlerRepository() {
        return resultHandlerRepository;
    }

    protected T executeSelectFromDataSource(String query,
                                            HandlerType handlerType,
                                            TransactionStatus transactionStatus) throws
                                                                                 DAOException {
        Connection connection = null;

        T result = null;

        try {
            connection = getConnection(transactionStatus);

            result = (T) sqlExecutor.select(connection, query,
                                            resultHandlerRepository.getResultHandler(
                                                    handlerType));
        } catch (DataSourceConnectorException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Getting connection is failed!", e);
        } catch (ExecutorException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Executing query is failed!", e);
        } catch (TransactionManagerException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Transaction is failed!", e);
        } finally {
            finalizeExecution(connection, transactionStatus);
        }

        return result;
    }

    protected void executeUpdateDataSource(String query, TransactionStatus transactionStatus) throws DAOException {
        Connection connection = null;

        try {
            connection = getConnection(transactionStatus);
            sqlExecutor.update(connection, query);
        } catch (DataSourceConnectorException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Getting connection is failed!", e);
        } catch (ExecutorException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Executing query is failed!", e);
        } catch (TransactionManagerException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Transaction is failed!", e);
        } finally {
            finalizeExecution(connection, transactionStatus);
        }
    }

    protected T executeSelectFromDataSource(PreparedStatement preparedStatement,
                                            HandlerType handlerType,
                                            TransactionStatus transactionStatus) throws DAOException {
        T result = null;

        try {
            result = (T) sqlExecutor.select(preparedStatement, resultHandlerRepository.getResultHandler(handlerType));
        } catch (ExecutorException e) {
            finalizeExecutionEmergencly(preparedStatement, transactionStatus);
        }

        return result;
    }

    protected void executeUpdateDataSource(PreparedStatement preparedStatement,
                                           TransactionStatus transactionStatus) throws DAOException {
        try {
            sqlExecutor.update(preparedStatement);
        } catch (ExecutorException e) {
            finalizeExecutionEmergencly(preparedStatement, transactionStatus);
            throw new DAOException("Executing query is failed!", e);
        }
    }

    protected PreparedStatement getPreparedStatement(String queryPattern, TransactionStatus transactionStatus) throws
                                                                                                               DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection(transactionStatus);

            preparedStatement = connection.prepareStatement(queryPattern);
        } catch (SQLException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Creating prepared statement is failed!", e);
        } catch (DataSourceConnectorException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Getting connection is failed!", e);
        } catch (TransactionManagerException e) {
            finalizeExecutionEmergencly(connection, transactionStatus);
            throw new DAOException("Transaction is failed!", e);
        }

        return preparedStatement;
    }

    protected PreparedStatement getCachedPreparedStatement(String queryPattern,
                                                           TransactionDAOOperationType transactionDAOOperationType,
                                                           TransactionStatus transactionStatus) throws DAOException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = transactionManager.getCachedPreparedStatementForTransaction(queryPattern,
                                                                                            transactionDAOOperationType);
        } catch (TransactionManagerException e) {
            finalizeExecutionEmergencly(preparedStatement, transactionStatus);
            throw new DAOException("Transaction is failed!", e);
        }

        return preparedStatement;
    }

    protected void finalizeExecution(PreparedStatement preparedStatement, TransactionStatus transactionStatus) throws
                                                                                                               DAOException {
        try {
            if (transactionStatus == TransactionStatus.OFF) {
                Connection connection = preparedStatement.getConnection();

                preparedStatement.close();

                connector.giveBackConnection(connection);
            } else if (transactionStatus == TransactionStatus.END || transactionStatus == TransactionStatus.END_CACHED_STATEMENT) {
                preparedStatement.close();

                transactionManager.giveBackTransactionConnection();
            }
        } catch (TransactionManagerException e) {
            throw new DAOException("Giving back transaction connection to the pool is failed!", e);
        } catch (SQLException e) {
            throw new DAOException("Giving back transaction connection to the pool is failed!", e);
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Giving back connection to the pool is failed!", e);
        }
    }

    private void finalizeExecution(Connection connection, TransactionStatus transactionStatus) throws DAOException {
        try {
            if (connection != null) {
                if (transactionStatus == TransactionStatus.OFF) {
                    connector.giveBackConnection(connection);
                } else if (transactionStatus == TransactionStatus.END || transactionStatus == TransactionStatus.END_CACHED_STATEMENT) {
                    transactionManager.giveBackTransactionConnection();
                }
            }
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Giving back connection to the pool is failed!", e);
        } catch (TransactionManagerException e) {
            throw new DAOException("Giving back transaction connection to the pool is failed!", e);
        }
    }

    private void finalizeExecutionEmergencly(Connection connection, TransactionStatus transactionStatus) throws
                                                                                                         DAOException {
        try {
            if (connection != null) {
                if (transactionStatus == TransactionStatus.OFF) {
                    connector.giveBackConnection(connection);
                } else {
                    transactionManager.giveBackEmergenclyTransactionConnection();
                }
            }
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Giving back connection to the pool is failed!", e);
        } catch (TransactionManagerException e) {
            throw new DAOException("Giving back transaction connection to the pool is failed!", e);
        }
    }

    private void finalizeExecutionEmergencly(PreparedStatement preparedStatement,
                                             TransactionStatus transactionStatus) throws DAOException {
        try {
            if (transactionStatus == TransactionStatus.OFF) {
                Connection connection = preparedStatement.getConnection();

                preparedStatement.close();

                connector.giveBackConnection(connection);
            } else {
                preparedStatement.close();

                transactionManager.giveBackEmergenclyTransactionConnection();
            }
        } catch (TransactionManagerException e) {
            throw new DAOException("Giving back transaction connection to the pool is failed!", e);
        } catch (SQLException e) {
            throw new DAOException("Giving back transaction connection to the pool is failed!", e);
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Giving back connection to the pool is failed!", e);
        }
    }

    private Connection getConnection(TransactionStatus transactionStatus) throws
                                                                          DataSourceConnectorException,
                                                                          TransactionManagerException {
        switch (transactionStatus) {
            case ON:
                return transactionManager.getConnectionForTransaction();

            case END:
                return transactionManager.getConnectionForTransaction();

            case ON_CACHED_STATEMENT:
                return transactionManager.getConnectionForTransaction();

            case END_CACHED_STATEMENT:
                return transactionManager.getConnectionForTransaction();

            case OFF:
                return connector.getConnection();

            default:
                return connector.getConnection();
        }
    }
}
