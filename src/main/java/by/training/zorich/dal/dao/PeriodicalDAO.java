package by.training.zorich.dal.dao;

import by.training.zorich.bean.Periodical;
import by.training.zorich.dal.dao.SearchCriteria.impl.PeriodicalSearchCriteria;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface PeriodicalDAO {
    void addPeriodicalTransactionaly(Periodical periodical) throws DAOException;

    Integer getLastInsertedPeriodicalIdTransactionaly() throws DAOException;

    void deletePeriodical(Periodical periodical) throws DAOException;

    Periodical getPeriodicalById(int idPeriodical) throws DAOException;

    List<Periodical> searchPeriodicalsTransactionaly(PeriodicalSearchCriteria periodicalSearchCriteria) throws
                                                                                                        DAOException;

    Integer getCountOfFoundPeriodicalsTransactionaly() throws DAOException;

    boolean validatePeriodicalByNameTransactionaly(String namePeriodical) throws DAOException;
}
