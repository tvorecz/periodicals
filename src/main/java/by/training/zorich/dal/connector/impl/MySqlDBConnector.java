package by.training.zorich.dal.connector.impl;

import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.DataSourceConnector;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class MySqlDBConnector implements DataSourceConnector {
    private ConcurrentLinkedQueue<Connection> freeConnections;
    private ConcurrentLinkedQueue<Connection> busyConnections;

    private String driverName;
    private String uri;
    private String file;
    private String user;
    private String password;
    private String parameter;
    private int poolSize;
    private long timeout;

    private MySqlDBConnector() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        driverName = dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_DRIVER.getName());
        uri = dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_URL.getName());
        //file = getClass().getResource(dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_FILE.getName())).getPath();
        file = dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_FILE.getName());
        user = dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_USER.getName());
        password = dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_PASSWORD.getName());
        parameter = dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_PARAMETER.getName());

        try {
            poolSize =
                    Integer.parseInt(dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_POOL_SIZE.getName()));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
        try {
            timeout =
                    Integer.parseInt(dbResourceManager.getResourceValue(MySqlDBNameParameter.DB_GETTING_TIMEOUT.getName()));
        } catch (NumberFormatException e) {
            timeout = 3000;
        }
    }

    private static class MySqlDBConnectorHelper {
        private static final MySqlDBConnector MY_SQL_DB_CONNECTOR = new MySqlDBConnector();
    }

    public static MySqlDBConnector getInstance() {
        return MySqlDBConnectorHelper.MY_SQL_DB_CONNECTOR;
    }

    @Override
    public void init() throws DataSourceConnectorException {
        try {
            Class.forName(driverName);

            freeConnections = new ConcurrentLinkedQueue<>();
            busyConnections = new ConcurrentLinkedQueue<>();

            for (int i = 0; i < poolSize; i++) {
                addNewConnectionToThePool();
            }

        } catch (ClassNotFoundException e) {
            throw new DataSourceConnectorException("Database driver class is not found.", e);
        } catch (SQLException e) {
            throw new DataSourceConnectorException("Problem with database during initializing connection pool.", e);
        }
    }

    @Override
    public Connection getConnection() throws DataSourceConnectorException {
        Connection connection = null;

        long timePass = 0;
        long timeStart = System.currentTimeMillis();

        while (connection == null && timePass <= timeout) {
            connection = freeConnections.poll();

            if (connection != null) {
                busyConnections.add(connection);
            }

            long timeEnd = System.currentTimeMillis();
            timePass = timeEnd - timeStart;
        }

        if (connection == null) {
            throw new DataSourceConnectorException("Timeout is over but there is not free connection.");
        }

        return connection;
    }

    @Override
    public void giveBackConnection(Connection connection) throws DataSourceConnectorException {
        try {
            if (connection.isClosed()) {
                throw new DataSourceConnectorException("Attempting to put closed connection to the pool.");
            }

            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if (!busyConnections.remove(connection)) {
                throw new DataSourceConnectorException("Error deleting from busy connection pool. One connection is missed.");
            }

            if (!freeConnections.offer(connection)) {
                throw new DataSourceConnectorException("Error returning connection to free connection pool. One connection is missed.");
            }
        } catch (SQLException e) {
            throw new DataSourceConnectorException("Error returning connection to free connection pool.");
        } finally {
            try {
                addNewConnectionToThePool();
            } catch (SQLException e) {
                throw new DataSourceConnectorException("Error returning connection to free connection pool. One connection is missed.");
            }
        }
    }

    @Override
    public void dispose() throws DataSourceConnectorException {
        try {
            closeConnectionQueue(busyConnections);
            closeConnectionQueue(freeConnections);
        } catch (SQLException e) {
            throw new DataSourceConnectorException("Error of closing connection.", e);
        }
    }

    private String createConnectionString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(uri);
        buffer.append(file);
        buffer.append(parameter);
        //buffer.append(user);
        //buffer.append(password);
        return buffer.toString();
    }

    private void addNewConnectionToThePool() throws SQLException {
        Connection connection = DriverManager.getConnection(createConnectionString(), user, password);
        PooledConnection pooledConnection = new PooledConnection(connection);
        freeConnections.add(pooledConnection);
    }

    private void closeConnectionQueue(ConcurrentLinkedQueue<Connection> connections) throws SQLException {
        Connection connection = null;

        while ((connection = connections.poll()) != null) {
            if(!connection.getAutoCommit()) {
                connection.commit();
            }

            connection.close();
        }
    }

    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void close() throws SQLException {
            connection.close();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws
                                                                                                           SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws
                                                                                                      SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback();
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws
                                                                                                                SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  int resultSetType,
                                                  int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql,
                                             int resultSetType,
                                             int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }
    }
}