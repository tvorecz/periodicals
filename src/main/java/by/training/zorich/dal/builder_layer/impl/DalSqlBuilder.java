package by.training.zorich.dal.builder_layer.impl;

import by.training.zorich.dal.builder_layer.DalBuilder;
import by.training.zorich.dal.connector.DataSourceConnectorException;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.impl.SQLiteDBConnector;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.dal.factory.impl.SQLiteDAOFactory;
import by.training.zorich.dal.sql_executor.Executor;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.impl.SQLExecutor;
import by.training.zorich.dal.sql_executor.impl.result_handler.ResultHandlerRepositoryImpl;

public class DalSqlBuilder implements DalBuilder {

    @Override
    public DAOFactory build() throws DAOException {
        DataSourceConnector connector = new SQLiteDBConnector();
        try {
            connector.init();
        } catch (DataSourceConnectorException e) {
            throw new DAOException("Creating of connector is failed.", e);
        }

        Executor executor = new SQLExecutor();
        ResultHandlerRepository handlerRepository = ResultHandlerRepositoryImpl.getInstance();

        DAOFactory factory = SQLiteDAOFactory.getInstance();
        factory.init(connector, executor, handlerRepository);

        return factory;
    }
}
