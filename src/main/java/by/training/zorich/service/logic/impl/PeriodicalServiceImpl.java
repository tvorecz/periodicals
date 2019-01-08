package by.training.zorich.service.logic.impl;

import by.training.zorich.bean.*;
import by.training.zorich.dal.dao.PeriodicalDAO;
import by.training.zorich.dal.dao.PeriodicalThemeDAO;
import by.training.zorich.dal.dao.PeriodicalTypeDAO;
import by.training.zorich.dal.dao.SubscriptionVariantDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.PeriodicalService;
import by.training.zorich.service.validator.Validator;
import by.training.zorich.service.validator.impl.periodical_validator.PeriodicalValidator;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {
    private final static String VALIDATE_FAILED = "User data is not valid!";

    private Validator<Periodical> periodicalValidator;
    private PeriodicalTypeDAO periodicalTypeDAO;
    private PeriodicalThemeDAO periodicalThemeDAO;
    private PeriodicalDAO periodicalDAO;


    public PeriodicalServiceImpl(DAOFactory daoFactory) {
        periodicalValidator = new PeriodicalValidator();
        periodicalThemeDAO = daoFactory.getPeriodicalThemeDAO();
        periodicalTypeDAO = daoFactory.getPeriodicalTypeDAO();
        periodicalDAO = daoFactory.getPeriodicalDAO();
    }

    @Override
    public void addNewPeriodical(Periodical periodical, ServiceResult serviceResult) throws ServiceException {
        if (periodicalValidator.validate(periodical)) {
            try {

                periodicalDAO.addPeriodicalTransactionaly(periodical);
                Integer newPeriodicalId = periodicalDAO.getLastInsertedPeriodicalIdTransactionaly();

                serviceResult.setResultObject(newPeriodicalId);
                serviceResult.setResultOperation(true);
            } catch (DAOException e) {
                serviceResult.setResultObject(false);
                throw new ServiceException("Saving periodical is failed!", e);
            }
        } else {
            serviceResult.setResultOperation(false);
            serviceResult.setResultMessage(VALIDATE_FAILED);
        }
    }

    @Override
    public void getPeriodicalById(int idPeriodical, ServiceResult serviceResult) throws ServiceException {
        Periodical periodical = null;

        try {
            periodical = periodicalDAO.getPeriodicalById(idPeriodical);

            if(periodical != null) {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(periodical);
            } else {
                serviceResult.setResultOperation(false);
            }

        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Getting periodical is failed!", e);
        }
    }

    @Override
    public void getAllPeriodicalTypes(ServiceResult serviceResult) throws ServiceException {
        try {
            List<PeriodicalType> periodicalTypeList = periodicalTypeDAO.getAllPeriodicalTypes();

            if (periodicalTypeList == null) {
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

            if (periodicalThemeList == null) {
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
