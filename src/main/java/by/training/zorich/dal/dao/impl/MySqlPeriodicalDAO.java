package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.Periodical;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.PeriodicalDAO;
import by.training.zorich.dal.dao.SearchCriteria.impl.PeriodicalSearchCriteria;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

import java.util.List;

public class MySqlPeriodicalDAO extends CommonDAO<Object> implements PeriodicalDAO {
    private final static String QUERY_VALIDATE_PERIODICAL_BY_NAME = "SELECT namePeriodical FROM periodicals WHERE namePeriodical = '%1$s'";
    private final static String QUERY_INSERT_PERIODICAL = "INSERT INTO periodicals (idType, idTheme, namePeriodical, periodicityInMonth, annotation, imagePath) values(%1$d, %2$d, '%3$s', %4$d, '%5$s', '%6$s')";
    private static final String QUERY_SELECT_LAST_PEDIOCAL_ID = "SELECT last_insert_id()";
    private static final String QUERY_SELECT_PERIODICAL_BY_ID = "SELECT \tperiodicals.idPeriodical,\n" +
                                                                "\t\tperiodicals.namePeriodical,\n" +
                                                                "\t\tperiodicals.periodicityInMonth,\n" +
                                                                "\t\tperiodicals.annotation,\n" +
                                                                "\t\tperiodicals.imagePath,\n" +
                                                                "\t\tperiodical_theme.idTheme,\n" +
                                                                "\t\tperiodical_theme.nameTheme,\n" +
                                                                "\t\tperiodical_type.idType,\n" +
                                                                "\t\tperiodical_type.typeName\n" +
                                                                "\t\t\n" +
                                                                "\t\tFROM periodicals\n" +
                                                                "\t\t\tJOIN periodical_type ON periodicals.idType = " +
                                                                "periodical_type.idType\n" +
                                                                "\t\t\tJOIN periodical_theme ON periodicals.idTheme =" +
                                                                " periodical_theme.idTheme\n" +
                                                                "\n" +
                                                                "\t\tWHERE periodicals.idPeriodical = %1$d";

    public MySqlPeriodicalDAO(DataSourceConnector connector,
                              TransactionManager transactionManager,
                              SQLExecutor sqlExecutor,
                              ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void addPeriodicalTransactionaly(Periodical periodical) throws DAOException {
        String query = String.format(QUERY_INSERT_PERIODICAL, periodical.getType().getId(), periodical.getTheme().getId(), periodical.getName(), periodical.getPeriodicityInMonth(), periodical.getAnnotation(), periodical.getImagePath());

        super.executeUpdateDataSource(query, TransactionStatus.ON);
    }

    @Override
    public Integer getLastInsertedPeriodicalIdTransactionaly() throws DAOException {
        return (Integer) super.executeSelectFromDataSource(QUERY_SELECT_LAST_PEDIOCAL_ID, HandlerType.LAST_INSERTED_ITEM_ID, TransactionStatus.ON);
    }

    @Override
    public Periodical getPeriodicalById(int idPeriodical) throws DAOException {
        String query = String.format(QUERY_SELECT_PERIODICAL_BY_ID, idPeriodical);

        return (Periodical) super.executeSelectFromDataSource(query, HandlerType.PERIODICAL_BY_ID, TransactionStatus.OFF);
    }

    @Override
    public void deletePeriodical(Periodical periodical) throws DAOException {

    }

    @Override
    public List<Periodical> searchPeriodicals(PeriodicalSearchCriteria periodicalSearchCriteria) throws DAOException {
        return null;
    }

    @Override
    public boolean validatePeriodicalByNameTransactionaly(String namePeriodical) throws DAOException {
        String query = String.format(QUERY_VALIDATE_PERIODICAL_BY_NAME, namePeriodical);

        return (Boolean) super.executeSelectFromDataSource(query, HandlerType.VALIDATE_HANDLER, TransactionStatus.ON);
    }
}
