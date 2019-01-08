package by.training.zorich.service.validator.impl.periodical_validator;

import by.training.zorich.bean.Periodical;
import by.training.zorich.dal.dao.PeriodicalDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.impl.SQLiteDAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.validator.Validator;

public class PeriodicalValidator implements Validator<Periodical> {
    private final static PeriodicalDAO PERIODICAL_DAO = SQLiteDAOFactory.getInstance().getPeriodicalDAO();

    @Override
    public boolean validate(Periodical objectForValidation) throws ServiceException {
        try {
            return (validatePeriodicalName(objectForValidation.getName()) && validatePeriodicity(objectForValidation.getPeriodicityInMonth()) && validateFileType(
                    objectForValidation.getImagePath()));
        } catch (DAOException e) {
            throw new ServiceException("Validation from DataSource is failed!", e);
        }
    }

    private boolean validatePeriodicalName(String name) throws DAOException {
        return PERIODICAL_DAO.validatePeriodicalByNameTransactionaly(name);
    }

    private boolean validatePeriodicity(int periodicity) {
        if (periodicity <= 31) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateFileType(String filePath) {
        if (filePath.contains(".jpg") || filePath.contains(".jpeg") || filePath.contains(".png") || filePath.contains(
                ".bmp")) {
            return true;
        } else {
            return false;
        }
    }
}
