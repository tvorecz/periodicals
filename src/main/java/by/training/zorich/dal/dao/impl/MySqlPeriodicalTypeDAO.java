package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.PeriodicalType;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.PeriodicalTypeDAO;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.util.List;

public class MySqlPeriodicalTypeDAO extends CommonDAO<Object> implements PeriodicalTypeDAO{
    private final static String QUERY_SELECT_ALL_TYPES = "SELECT * FROM periodical_type";

    public MySqlPeriodicalTypeDAO(DataSourceConnector connector,
                                  TransactionManager transactionManager,
                                  SQLExecutor sqlExecutor,
                                  ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public List<PeriodicalType> getAllPeriodicalTypes() throws DAOException {
        return (List<PeriodicalType>) super.executeSelectFromDataSource(QUERY_SELECT_ALL_TYPES, HandlerType.ALL_PERIODICAL_TYPES, TransactionStatus.OFF);
    }
}
