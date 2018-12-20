package by.training.zorich.service.logic.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.bean.UserRole;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.UserService;
import by.training.zorich.service.password_encoder.PasswordEncoder;
import by.training.zorich.service.password_encoder.impl.PasswordEncodeImpl;
import by.training.zorich.service.validator.Validator;
import by.training.zorich.service.validator.impl.user_validator.RegistratingUserValidator;
import by.training.zorich.service.validator.impl.user_validator.UserValidator;

public class UserServiceImpl implements UserService {
    private Validator<User> userValidator;
    private Validator<User> registratingUserValidator;
    private PasswordEncoder passwordEncoder;
    private UserDAO userDAO;

    public UserServiceImpl(DAOFactory daoFactory) {
        userValidator = new UserValidator();
        registratingUserValidator = new RegistratingUserValidator();
        passwordEncoder = new PasswordEncodeImpl();
        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void register(User user, ServiceResult serviceResult) throws ServiceException {
        if (registratingUserValidator.validate(user)) {
            user.setCodifiedPassword(passwordEncoder.encodePassword(user.getRealPassword()));
            user.setRole(UserRole.SUBSCRIBER);
            try {
                userDAO.register(user);
                serviceResult.setResultOperation(true);
            } catch (DAOException e) {
                serviceResult.setResultOperation(false);
                throw new ServiceException("Registration is failed!", e);
            }
        } else {
            serviceResult.setResultOperation(false);
        }
    }

    @Override
    public void authenticate(User user, ServiceResult serviceResult) throws ServiceException {
        if (userValidator.validate(user)) {
            user.setCodifiedPassword(passwordEncoder.encodePassword(user.getRealPassword()));
            try {
                User authenticateUser = userDAO.authenticate(user);
                if (authenticateUser == null) {
                    serviceResult.setResultOperation(false);
                } else {
                    serviceResult.setResultOperation(true);
                    serviceResult.setResultObject(authenticateUser);
                }
            } catch (DAOException e) {
                serviceResult.setResultOperation(false);
                throw new ServiceException("Authenticate is failed!", e);
            }
        } else {
            serviceResult.setResultOperation(false);
        }
    }
}
