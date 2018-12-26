package by.training.zorich.dal.factory.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.SubscriptionDAO;
import by.training.zorich.dal.dao.UserAddressDAO;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.dao.UserRoleDAO;
import by.training.zorich.dal.dao.impl.MySqlSubscriptionDAO;
import by.training.zorich.dal.dao.impl.MySqlUserAddressDAO;
import by.training.zorich.dal.dao.impl.MySqlUserDAO;
import by.training.zorich.dal.dao.impl.MySqlUserRoleDAO;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public class SQLiteDAOFactory implements DAOFactory {
    private static UserDAO userDAOImpl;
    private static UserRoleDAO userRoleDAOImpl;
    private static UserAddressDAO userAddressDAOImpl;
    private static SubscriptionDAO subscriptionDAOImpl;

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
                     SQLExecutor sqlExecutor,
                     ResultHandlerRepository resultHandlerRepository) {
        userDAOImpl = new MySqlUserDAO(connector, sqlExecutor, resultHandlerRepository);
        userRoleDAOImpl = new MySqlUserRoleDAO(connector, sqlExecutor, resultHandlerRepository);
        userAddressDAOImpl = new MySqlUserAddressDAO(connector,sqlExecutor, resultHandlerRepository);
        subscriptionDAOImpl = new MySqlSubscriptionDAO(connector, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAOImpl;
    }

    @Override
    public UserRoleDAO getUserRoleDAO() {
        return userRoleDAOImpl;
    }

    @Override
    public UserAddressDAO getUserAddressDAO() {
        return userAddressDAOImpl;
    }

    @Override
    public SubscriptionDAO getSubscriptionDAO() {
        return subscriptionDAOImpl;
    }
}
