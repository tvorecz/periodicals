package by.training.zorich.dal.connector;

import by.training.zorich.dal.connector.impl.TransactionDAOOperationType;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface TransactionManager {
    public void init(DataSourceConnector dataSourceConnector);

    public Connection getConnectionForTransaction() throws TransactionManagerException;

    public PreparedStatement getCachedPreparedStatementForTransaction(String queryPattern,
                                                                      TransactionDAOOperationType transactionDAOOperationType) throws
                                                                                                                               TransactionManagerException;

    public void giveBackTransactionConnection() throws TransactionManagerException;

    public void giveBackEmergenclyTransactionConnection() throws TransactionManagerException;
}
