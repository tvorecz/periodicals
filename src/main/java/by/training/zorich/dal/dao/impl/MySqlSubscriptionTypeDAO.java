package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.SubscriptionType;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.SubscriptionTypeDAO;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.util.List;

public class MySqlSubscriptionTypeDAO extends CommonDAO<Object> implements SubscriptionTypeDAO {
    private final static String QUERY_SELECT_ALL_SUBSCRIPTION_TYPES = "SELECT * FROM subscription_types";
    private final static String QUERY_SELECT_SUBSCRIPTION_TYPE_BY_ID = "SELECT * FROM subscription_types WHERE idSubscriptionType = %1$d";

    public MySqlSubscriptionTypeDAO(DataSourceConnector connector,
                                    TransactionManager transactionManager,
                                    SQLExecutor sqlExecutor,
                                    ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public List<SubscriptionType> getAllSubscriptionTypes() throws DAOException {
        return (List<SubscriptionType>) super.executeSelectFromDataSource(QUERY_SELECT_ALL_SUBSCRIPTION_TYPES,
                                                                          HandlerType.ALL_SUBSCRIPTION_TYPES, TransactionStatus.OFF);
    }

    @Override
    public SubscriptionType getSubscriptionTypeByIdTransactionaly(int id) throws DAOException {
        String query = String.format(QUERY_SELECT_SUBSCRIPTION_TYPE_BY_ID, id);
        List<SubscriptionType> subscriptionTypeList = (List<SubscriptionType>) super.executeSelectFromDataSource(query, HandlerType.ALL_SUBSCRIPTION_TYPES, TransactionStatus.ON);
        return subscriptionTypeList.get(0) ;
    }
}
