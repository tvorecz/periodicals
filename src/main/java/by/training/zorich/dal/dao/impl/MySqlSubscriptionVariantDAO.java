package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.SubsciptionVariant;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.SubscriptionVariantDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

public class MySqlSubscriptionVariantDAO extends CommonDAO<SubsciptionVariant> implements SubscriptionVariantDAO {
    public MySqlSubscriptionVariantDAO(DataSourceConnector connector,
                                       TransactionManager transactionManager,
                                       SQLExecutor sqlExecutor,
                                       ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public SubsciptionVariant getSubscriptionVariantById(int idSubscriptionVariant) throws DAOException {
        return null;
    }
}
