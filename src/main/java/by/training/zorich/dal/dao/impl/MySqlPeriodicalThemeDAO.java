package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.PeriodicalTheme;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.PeriodicalThemeDAO;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.util.List;

public class MySqlPeriodicalThemeDAO extends CommonDAO<Object> implements PeriodicalThemeDAO {
    private final static String QUERY_SELECT_ALL_THEMES = "SELECT * FROM periodical_theme";

    public MySqlPeriodicalThemeDAO(DataSourceConnector connector,
                                   TransactionManager transactionManager,
                                   SQLExecutor sqlExecutor,
                                   ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public List<PeriodicalTheme> getAllPeriodicalThemes() throws DAOException {
        return (List<PeriodicalTheme>) super.executeSelectFromDataSource(QUERY_SELECT_ALL_THEMES,
                                                                         HandlerType.ALL_PERIODICAL_THEMES,
                                                                         TransactionStatus.OFF);
    }
}
