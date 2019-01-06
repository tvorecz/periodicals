package by.training.zorich.service.logic.impl;

import by.training.zorich.bean.PeriodicalTheme;
import by.training.zorich.bean.PeriodicalType;
import by.training.zorich.bean.ServiceResult;
import by.training.zorich.dal.dao.PeriodicalThemeDAO;
import by.training.zorich.dal.dao.PeriodicalTypeDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.PeriodicalService;

import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {
    private PeriodicalTypeDAO periodicalTypeDAO;
    private PeriodicalThemeDAO periodicalThemeDAO;

    public PeriodicalServiceImpl(DAOFactory daoFactory) {
        periodicalThemeDAO = daoFactory.getPeriodicalThemeDAO();
        periodicalTypeDAO = daoFactory.getPeriodicalTypeDAO();
    }

    @Override
    public void getAllPeriodicalTypes(ServiceResult serviceResult) throws ServiceException {
        try {
            List<PeriodicalType> periodicalTypeList = periodicalTypeDAO.getAllPeriodicalTypes();

            if(periodicalTypeList == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(periodicalTypeList);
            }

        } catch (DAOException e) {
            serviceResult.setResultObject(false);
            throw new ServiceException("Getting periodical types is failed!", e);
        }
    }

    @Override
    public void getAllPeriodicalThemes(ServiceResult serviceResult) throws ServiceException {
        try {
            List<PeriodicalTheme> periodicalThemeList = periodicalThemeDAO.getAllPeriodicalThemes();

            if(periodicalThemeList == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(periodicalThemeList);
            }

        } catch (DAOException e) {
            serviceResult.setResultObject(false);
            throw new ServiceException("Getting periodical themes is failed!", e);
        }
    }
}
