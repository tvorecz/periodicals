/**
 * Provides access to dao-classes. Using pattern singleton.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.dal.factory.impl;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.*;
import by.training.zorich.dal.dao.impl.*;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public class MySqlDAOFactory implements DAOFactory {
    private static UserDAO userDAOImpl;
    private static UserRoleDAO userRoleDAOImpl;
    private static UserAddressDAO userAddressDAOImpl;
    private static SubscriptionDAO subscriptionDAOImpl;
    private static PeriodicalThemeDAO periodicalThemeDAOImpl;
    private static PeriodicalTypeDAO periodicalTypeDAOImpl;
    private static SubscriptionTypeDAO subscriptionTypeDAOImpl;
    private static PeriodicalDAO periodicalDAOImpl;
    private static SubscriptionVariantDAO subscriptionVariantDAOImpl;
    private static PaymentDAO paymentDAOImpl;

    private MySqlDAOFactory() {
    }

    private static class SQLiteDAOFactoryHelper {
        private static final MySqlDAOFactory FACTORY = new MySqlDAOFactory();
    }

    public static MySqlDAOFactory getInstance() {
        return SQLiteDAOFactoryHelper.FACTORY;
    }

    @Override
    public void init(DataSourceConnector connector,
                     TransactionManager transactionManager,
                     SQLExecutor sqlExecutor,
                     ResultHandlerRepository resultHandlerRepository) {
        userDAOImpl = new MySqlUserDAO(connector, transactionManager, sqlExecutor, resultHandlerRepository);
        userRoleDAOImpl = new MySqlUserRoleDAO(connector, transactionManager, sqlExecutor, resultHandlerRepository);
        userAddressDAOImpl = new MySqlUserAddressDAO(connector,
                                                     transactionManager,
                                                     sqlExecutor,
                                                     resultHandlerRepository);
        subscriptionDAOImpl = new MySqlSubscriptionDAO(connector,
                                                       transactionManager,
                                                       sqlExecutor,
                                                       resultHandlerRepository);
        periodicalThemeDAOImpl = new MySqlPeriodicalThemeDAO(connector,
                                                             transactionManager,
                                                             sqlExecutor,
                                                             resultHandlerRepository);
        periodicalTypeDAOImpl = new MySqlPeriodicalTypeDAO(connector,
                                                           transactionManager,
                                                           sqlExecutor,
                                                           resultHandlerRepository);
        subscriptionTypeDAOImpl = new MySqlSubscriptionTypeDAO(connector,
                                                               transactionManager,
                                                               sqlExecutor,
                                                               resultHandlerRepository);
        periodicalDAOImpl = new MySqlPeriodicalDAO(connector, transactionManager, sqlExecutor, resultHandlerRepository);
        subscriptionVariantDAOImpl = new MySqlSubscriptionVariantDAO(connector,
                                                                     transactionManager,
                                                                     sqlExecutor,
                                                                     resultHandlerRepository);
        paymentDAOImpl = new MySqlPaymentDAO(connector, transactionManager, sqlExecutor, resultHandlerRepository);
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

    @Override
    public PeriodicalTypeDAO getPeriodicalTypeDAO() {
        return periodicalTypeDAOImpl;
    }

    @Override
    public PeriodicalThemeDAO getPeriodicalThemeDAO() {
        return periodicalThemeDAOImpl;
    }

    @Override
    public SubscriptionTypeDAO getSubscriptionTypeDAO() {
        return subscriptionTypeDAOImpl;
    }

    @Override
    public PeriodicalDAO getPeriodicalDAO() {
        return periodicalDAOImpl;
    }

    @Override
    public SubscriptionVariantDAO getSubscriptionVariantDAO() {
        return subscriptionVariantDAOImpl;
    }

    @Override
    public PaymentDAO getPaymentDAO() {
        return paymentDAOImpl;
    }
}
