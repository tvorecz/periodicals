package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.Payment;
import by.training.zorich.bean.SubsciptionVariant;
import by.training.zorich.bean.UserAddress;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.dao.SubscriptionDAO;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.util.List;

public class MySqlSubscriptionDAO extends CommonDAO<Object> implements SubscriptionDAO {



    public MySqlSubscriptionDAO(DataSourceConnector connector,
                                SQLExecutor sqlExecutor,
                                ResultHandlerRepository resultHandlerRepository) {
        super(connector, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public Payment subscribeTransactionaly(SubsciptionVariant subscriptionVariant, UserAddress address) {
        return null;
    }

    @Override
    public Payment subscribeTransactionaly(List<SubsciptionVariant> subscriptionVariants, UserAddress address) {
        return null;
    }

    private void createSubscription() {

    }
}
