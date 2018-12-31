package by.training.zorich.dal.dao;

import by.training.zorich.bean.Periodical;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface PeriodicalDAO {
    void addPeriodical(Periodical periodical) throws DAOException;
    void deletePeriodical(Periodical periodical) throws DAOException;
    Periodical getPeriodicalById(int idPeriodical) throws DAOException;
    List<Periodical> searchPeriodicals(String keySearch, int idTheme, int idType, int beginOfRange, int endOfRange) throws DAOException;

    List<Periodical> getAllPeriodicals(int beginOfRange, int endOfRange) throws DAOException;
    List<Periodical> searchPeriodicals(String keySearch, int beginOfRange, int endOfRange) throws DAOException;
    List<Periodical> getPeriodicalsByThemeId(int idTheme, int beginOfRange, int endOfRange) throws DAOException;
    List<Periodical> getPeriodicalsByTypeId(int idType, int beginOfRange, int endOfRange) throws DAOException;

}
