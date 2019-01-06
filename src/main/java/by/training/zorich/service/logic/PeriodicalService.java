package by.training.zorich.service.logic;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.service.exception.ServiceException;

public interface PeriodicalService {
    void getAllPeriodicalTypes(ServiceResult serviceResult) throws ServiceException;
    void getAllPeriodicalThemes(ServiceResult serviceResult) throws ServiceException;
}
