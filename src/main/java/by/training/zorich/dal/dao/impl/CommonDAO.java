package by.training.zorich.dal.dao.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;

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

    protected T executeSimpleSelect(String query, HandlerType handlerType, TransactionStatus transactionStatus) throws
                                                                                                                DAOException {
        Connection connection = null;

        try {
            connection = getConnection(transactionStatus);

            return (T) sqlExecutor.select(connection, query,
                                          resultHandlerRepository.getResultHandler(
                                                  handlerType));
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            throw new DAOException("Getting information is failed!", e);
        } finally {
            releaseConnection(connection, transactionStatus);
        }
    }

    protected void executeSimpleUpdate(String query, TransactionStatus transactionStatus) throws DAOException {
        Connection connection = null;

        try {
            connection = getConnection(transactionStatus);
            sqlExecutor.update(connection, query);
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            throw new DAOException("Operation is failed!", e);
        } finally {
            releaseConnection(connection, transactionStatus);
        }
    }

    private Connection getConnection(TransactionStatus transactionStatus) throws DataSourceConnectorException {
        switch (transactionStatus) {
            case ON:
                return connector.getConnectionForTransaction();

            case OFF:
                return connector.getConnection();

            default:
                return connector.getConnection();
        }
    }

    private void releaseConnection(Connection connection, TransactionStatus transactionStatus) throws DAOException {
        if (connection != null) {
            try {
                if(transactionStatus.equals(TransactionStatus.ON)) {
                    connection.rollback();
                }

                connector.giveBackConnection(connection);
            } catch (DataSourceConnectorException e) {
                throw new DAOException("Giving back connection to the pool is failed!", e);
            } catch (SQLException e) {
                throw new DAOException("Rollback connection is failed!", e);
            }
        }
    }
}
