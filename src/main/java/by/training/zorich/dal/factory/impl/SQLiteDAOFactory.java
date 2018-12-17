package by.training.zorich.dal.factory.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.dao.UserRoleDAO;
import by.training.zorich.dal.dao.impl.SQLiteUserDAO;
import by.training.zorich.dal.dao.impl.SQLiteUserRoleDAO;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public class SQLiteDAOFactory implements DAOFactory {
    private static UserDAO userDAOImpl;
    private static UserRoleDAO userRoleDAOImpl;

    private SQLiteDAOFactory() {
    }

    private static class SQLiteDAOFactoryHelper {
        private static final SQLiteDAOFactory FACTORY = new SQLiteDAOFactory();
    }

    public static SQLiteDAOFactory getInstance() {
        return SQLiteDAOFactoryHelper.FACTORY;
    }

    @Override
    public void init(DataSourceConnector connector,
                     SQLExecutor SQLExecutor,
                     ResultHandlerRepository resultHandlerRepository) {
        userDAOImpl = new SQLiteUserDAO(connector, SQLExecutor, resultHandlerRepository);
        userRoleDAOImpl = new SQLiteUserRoleDAO(connector, SQLExecutor, resultHandlerRepository);
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAOImpl;
    }

    @Override
    public UserRoleDAO getUserRoleDAO() {
        return userRoleDAOImpl;
    }
}
