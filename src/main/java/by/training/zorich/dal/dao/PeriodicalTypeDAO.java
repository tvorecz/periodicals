package by.training.zorich.dal.dao;

import by.training.zorich.bean.PeriodicalType;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface PeriodicalTypeDAO {
    List<PeriodicalType> getAllPeriodicalTypes() throws DAOException;
}
