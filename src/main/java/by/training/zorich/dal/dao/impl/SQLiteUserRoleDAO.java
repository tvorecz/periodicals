package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.UserRole;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.UserRoleDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.exception.ExecutorException;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

import java.sql.Connection;

public class SQLiteUserRoleDAO implements UserRoleDAO {
    private final static String QUERY_USER_ROLE = "SELECT idRole FROM user_roles WHERE nameRole = '%1$s'";

    private DataSourceConnector connector; //provide connection from pool
    private SQLExecutor SQLExecutor; //execute query to data source and
    private ResultHandlerRepository resultHandlerRepository; //provide handler for handle resultset

    public SQLiteUserRoleDAO(DataSourceConnector connector,
                             SQLExecutor SQLExecutor,
                             ResultHandlerRepository resultHandlerRepository) {
        this.connector = connector;
        this.SQLExecutor = SQLExecutor;
        this.resultHandlerRepository = resultHandlerRepository;
    }

    @Override
    public int getIdRoleByName(String roleName) throws DAOException {
        String query = String.format(QUERY_USER_ROLE, UserRole.SUBSCRIBER.getName());
        Connection connection = null;

        try {
            connection = connector.getConnection();
            return (Integer) SQLExecutor.select(connection, query, resultHandlerRepository.getResultHandler(HandlerType.ID_USER_ROLE_HANDLER));
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Getting of connection from the pool is failed!", e);
        } catch (ExecutorException e) {
            throw new DAOException("Getting of id role id failed!", e);
        } finally {
            if(connection != null) {
                try {
                    connector.giveBackConnection(connection);
                } catch (DataSourceConnectorException e) {
                    throw new DAOException("Giving back connection to the pool is failed!", e);
                }
            }
        }
    }
}
