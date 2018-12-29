package by.training.zorich.dal.builder_layer.impl;

import by.training.zorich.dal.builder_layer.DalBuilder;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.connector.impl.MySqlDBConnector;
import by.training.zorich.dal.connector.impl.TransactionManagerImpl;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.dal.factory.impl.SQLiteDAOFactory;
import by.training.zorich.dal.sql_executor.SQLExecutor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.impl.MySqlExecutor;
import by.training.zorich.dal.sql_executor.impl.result_handler.ResultHandlerRepositoryImpl;

public class DalSQLiteBuilder implements DalBuilder {

    @Override
    public DAOFactory build() throws DAOException {
        DataSourceConnector connector = MySqlDBConnector.getInstance();
        try {
            connector.init();
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Creating of connector is failed.", e);
        }

        SQLExecutor SQLExecutor = MySqlExecutor.getInstance();
        ResultHandlerRepository handlerRepository = ResultHandlerRepositoryImpl.getInstance();
        TransactionManager transactionManager = TransactionManagerImpl.getInstance();
        transactionManager.init(connector);

        DAOFactory factory = SQLiteDAOFactory.getInstance();
        factory.init(connector, transactionManager, SQLExecutor, handlerRepository);

        return factory;
    }
}
