package by.training.zorich.dal.dao;

import by.training.zorich.bean.PeriodicalTheme;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface PeriodicalThemeDAO {
    List<PeriodicalTheme> getAllPeriodicalThemes() throws DAOException;
}
