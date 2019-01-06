package by.training.zorich.dal.factory;

import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.*;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;

public interface DAOFactory {
    void init(DataSourceConnector connector,
              TransactionManager transactionManager,
              SQLExecutor sqlExecutor,
              ResultHandlerRepository resultHandlerRepository);

    UserDAO getUserDAO();

    UserRoleDAO getUserRoleDAO();

    UserAddressDAO getUserAddressDAO();

    SubscriptionDAO getSubscriptionDAO();

    PeriodicalTypeDAO getPeriodicalTypeDAO();

    PeriodicalThemeDAO getPeriodicalThemeDAO();

    SubscriptionTypeDAO getSubscriptionTypeDAO();
}
