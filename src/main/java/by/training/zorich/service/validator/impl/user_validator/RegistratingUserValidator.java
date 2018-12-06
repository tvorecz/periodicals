package by.training.zorich.service.validator.impl.user_validator;

import by.training.zorich.bean.User;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.impl.SQLiteDAOFactory;
import by.training.zorich.service.exception.ServiceException;

public class RegistratingUserValidator extends UserValidator {
    private static final UserDAO userDAO = SQLiteDAOFactory.getInstance().getUserDAO();

    @Override
    public boolean validate(User objectForValidation) throws ServiceException {
        boolean formValidation = super.validate(objectForValidation);

        if(formValidation) {
            try {
                return userDAO.validate(objectForValidation);
            } catch (DAOException e) {
                throw new ServiceException("Validation from DataSource is failed!", e);
            }
        }

        return false;
    }
}
