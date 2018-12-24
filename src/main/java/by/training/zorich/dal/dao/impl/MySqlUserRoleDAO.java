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

public class MySqlUserRoleDAO extends CommonDAO<Integer> implements UserRoleDAO {
    private final static String QUERY_USER_ROLE = "SELECT idRole FROM user_roles WHERE nameRole = '%1$s'";


    public MySqlUserRoleDAO(DataSourceConnector connector,
                            SQLExecutor sqlExecutor,
                            ResultHandlerRepository resultHandlerRepository) {
        super(connector, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public int getIdRoleByName(String roleName) throws DAOException {
        String query = String.format(QUERY_USER_ROLE, UserRole.SUBSCRIBER.getName());

        return super.executeSimpleSelect(query, HandlerType.ID_USER_ROLE_HANDLER);
    }
}
