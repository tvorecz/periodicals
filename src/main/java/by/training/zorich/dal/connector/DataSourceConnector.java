package by.training.zorich.dal.connector;

import java.sql.Connection;

public interface DataSourceConnector {
    void init() throws DataSourceConnectorException;
    Connection getConnection() throws DataSourceConnectorException;
    void giveBackConnection(Connection connection) throws DataSourceConnectorException;
    void dispose() throws DataSourceConnectorException;
}
