package by.training.zorich.service.validator.impl.user_validator;

import by.training.zorich.bean.User;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.impl.MySqlDAOFactory;
import by.training.zorich.service.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistratingUserValidator extends UserValidator {
    private static final UserDAO USER_DAO = MySqlDAOFactory.getInstance().getUserDAO();

    private static final String EMAIL_PATTERN = "(([a-z\\.\\-0-9]{2,})(@)([a-z]{2,})(.))([a-z]{2,})";
    private Pattern emailPattern;

    public RegistratingUserValidator() {
        emailPattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public boolean validate(User objectForValidation) throws ServiceException {
        boolean formValidation = super.validate(objectForValidation);

        if(formValidation) {
            try {
                return validateEmailForm(objectForValidation.getEmail()) && isDublicateInDataBase(objectForValidation);
            } catch (DAOException e) {
                throw new ServiceException("Validation from DataSource is failed!", e);
            }
        }

        return false;
    }

    private boolean validateEmailForm(String email) {
        Matcher matcher = emailPattern.matcher(email);
        if(matcher.find()) {
            if(matcher.group().equals(email)) {
                return true;
            }
        }

        return false;
    }

    private boolean isDublicateInDataBase(User objectForValidation) throws DAOException {
        return USER_DAO.validate(objectForValidation);
    }
}
