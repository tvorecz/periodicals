package by.training.zorich.dal.dao;

import by.training.zorich.bean.Periodical;
import by.training.zorich.dal.dao.SearchCriteria.impl.PeriodicalSearchCriteria;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface PeriodicalDAO {
    void addPeriodical(Periodical periodical) throws DAOException;
    void deletePeriodical(Periodical periodical) throws DAOException;
    Periodical getPeriodicalById(int idPeriodical) throws DAOException;
    List<Periodical> searchPeriodicals(PeriodicalSearchCriteria periodicalSearchCriteria) throws DAOException;
}
