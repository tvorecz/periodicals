package by.training.zorich.dal.dao.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.PreparedStatementFillerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class CommonDAO<T> {
    private DataSourceConnector connector; //provide connection from pool
    private by.training.zorich.dal.sql_executor.SQLExecutor sqlExecutor; //execute query to data source and
    private ResultHandlerRepository resultHandlerRepository; //provide handler for handle resultset

    public CommonDAO(DataSourceConnector connector,
                     SQLExecutor sqlExecutor,
                     ResultHandlerRepository resultHandlerRepository) {
        this.connector = connector;
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

    protected T executeSelect(String query, HandlerType handlerType, TransactionStatus transactionStatus) throws
                                                                                                                DAOException {
        Connection connection = null;

        try {
            connection = getConnection(transactionStatus);

            return (T) sqlExecutor.select(connection, query,
                                          resultHandlerRepository.getResultHandler(
                                                  handlerType));


        } catch (DataSourceConnectorException e) {
            rollbackConnection(connection, transactionStatus);
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            rollbackConnection(connection, transactionStatus);
            throw new DAOException("Getting information is failed!", e);
        } finally {
            releaseConnection(connection, transactionStatus);
        }
    }

    protected void executeUpdate(String query, TransactionStatus transactionStatus) throws DAOException {
        Connection connection = null;

        try {
            connection = getConnection(transactionStatus);
            sqlExecutor.update(connection, query);
        } catch (DataSourceConnectorException e) {
            rollbackConnection(connection, transactionStatus);
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            rollbackConnection(connection, transactionStatus);
            throw new DAOException("Operation is failed!", e);
        } finally {
            releaseConnection(connection, transactionStatus);
        }
    }

    protected T executeSelect(PreparedStatement preparedStatement,
                              HandlerType handlerType,
                              TransactionStatus transactionStatus) throws DAOException {
        try {
            return (T) sqlExecutor.select(preparedStatement, resultHandlerRepository.getResultHandler(handlerType));
        } catch (ExecutorException e) {
            rollbackConnection(preparedStatement, transactionStatus);
            throw new DAOException("Getting information is failed!", e);
        } finally {
            releaseConnection(preparedStatement, transactionStatus);
        }
    }

    protected void executeUpdate(PreparedStatement preparedStatement, TransactionStatus transactionStatus) throws DAOException {
        try {
            sqlExecutor.update(preparedStatement);
        } catch (ExecutorException e) {
            rollbackConnection(preparedStatement, transactionStatus);
            throw new DAOException("Operation is failed!", e);
        } finally {
            releaseConnection(preparedStatement, transactionStatus);
        }
    }

    protected PreparedStatement createPreparedStatement(String queryPattern, TransactionStatus transactionStatus) throws DAOException {
        Connection connection = null;

        try {
            connection = getConnection(transactionStatus);

            PreparedStatement preparedStatement = connection.prepareStatement(queryPattern);

            return preparedStatement;
        } catch (SQLException e) {
            rollbackConnection(connection, transactionStatus);
            throw new DAOException("Creating of PreparedStatement is failed!", e);
        } catch (DataSourceConnectorException e) {
            rollbackConnection(connection, transactionStatus);
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } finally {
            releaseConnection(connection, transactionStatus);
        }
    }

    protected void fillPreparedStatement(PreparedStatement preparedStatement,
                                         List<Object> valuesForFillingStatement,
                                         List<PreparedStatementFillerType> valuesTypes,
                                         TransactionStatus transactionStatus) throws
                                                                                                                                                                     DAOException {
        Iterator<Object> valuesIterator = valuesForFillingStatement.iterator();
        Iterator<PreparedStatementFillerType> typesIterator = valuesTypes.iterator();

        int parameterIndex = 1;

        while (valuesIterator.hasNext() && typesIterator.hasNext()) {
            try {
                switch (typesIterator.next()) {
                    case BYTE:
                        preparedStatement.setByte(parameterIndex, (Byte) valuesIterator.next());
                        break;

                    case DATE:
                        preparedStatement.setDate(parameterIndex, (Date) valuesIterator.next());
                        break;

                    case LONG:
                        preparedStatement.setLong(parameterIndex, (Long) valuesIterator.next());
                        break;

                    case DOUBLE:
                        preparedStatement.setDouble(parameterIndex, (Double) valuesIterator.next());
                        break;

                    case STRING:
                        preparedStatement.setString(parameterIndex, (String) valuesIterator.next());
                        break;

                    case BOOLEAN:
                        preparedStatement.setBoolean(parameterIndex, (Boolean) valuesIterator.next());
                        break;

                    case INTEGER:
                        preparedStatement.setInt(parameterIndex, (Integer) valuesIterator.next());

                        break;
                }
            } catch (SQLException e) {
                rollbackConnection(preparedStatement, transactionStatus);
                throw new DAOException("Setting prepared statement parameters is failed!", e);
            }

            ++parameterIndex;
        }

    }

    private Connection getConnection(TransactionStatus transactionStatus) throws DataSourceConnectorException {
        switch (transactionStatus) {
            case ON:
                return connector.getConnectionForTransaction();

            case END:
                return  connector.getConnectionForTransaction();

            case OFF:
                return connector.getConnection();

            default:
                return connector.getConnection();
        }
    }

    private void releaseConnection(Connection connection, TransactionStatus transactionStatus) throws DAOException {
        try {
            if(connection != null) {
                if (transactionStatus.equals(TransactionStatus.END)) {
                    connector.giveBackTransactionConnection(connection);
                } else if (transactionStatus.equals(TransactionStatus.OFF)){
                    connector.giveBackConnection(connection);
                }
            }
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Giving back connection to the pool is failed!", e);
        }
    }

    private void rollbackConnection(Connection connection, TransactionStatus transactionStatus) throws DAOException {
        if(transactionStatus.equals(TransactionStatus.ON) || transactionStatus.equals(TransactionStatus.END)) {
            try {
                connector.giveBackEmergenclyTransactionConnection(connection);

                releaseConnection(connection, TransactionStatus.END);
            } catch (DataSourceConnectorException e) {
                throw new DAOException("Giving back connection to the pool is failed!", e);
            }
        }
    }

    private void releaseConnection(PreparedStatement preparedStatement, TransactionStatus transactionStatus) throws DAOException {
        Connection connection = null;
        try {
            connection = preparedStatement.getConnection();

            releaseConnection(connection, transactionStatus);
        } catch (SQLException e) {
            throw new DAOException("Getting connection from statement is failed!", e);
        }
    }

    private void rollbackConnection(PreparedStatement preparedStatement, TransactionStatus transactionStatus) throws DAOException {
        Connection connection = null;
        try {
            connection = preparedStatement.getConnection();

            rollbackConnection(connection, transactionStatus);
        } catch (SQLException e) {
            throw new DAOException("Getting connection from statement is failed!", e);
        }
    }
}
