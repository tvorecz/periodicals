package by.training.zorich.service.logic.impl;

import by.training.zorich.bean.*;
import by.training.zorich.dal.dao.UserAddressDAO;
import by.training.zorich.dal.dao.UserDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.UserService;
import by.training.zorich.service.util.PasswordEncoder;
import by.training.zorich.service.util.impl.PasswordEncodeImpl;
import by.training.zorich.service.validator.Validator;
import by.training.zorich.service.validator.impl.user_validator.RegistratingUserValidator;
import by.training.zorich.service.validator.impl.user_validator.UserAddressValidator;
import by.training.zorich.service.validator.impl.user_validator.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static String VALIDATE_FAILED = "User data is not valid!";

    private Validator<User> userValidator;
    private Validator<User> registratingUserValidator;
    private Validator<UserAddress> userAddressValidator;
    private PasswordEncoder passwordEncoder;
    private UserDAO userDAO;
    private UserAddressDAO userAddressDAO;

    public UserServiceImpl(DAOFactory daoFactory) {
        userValidator = new UserValidator();
        registratingUserValidator = new RegistratingUserValidator();
        userAddressValidator = new UserAddressValidator();

        passwordEncoder = new PasswordEncodeImpl();
        userDAO = daoFactory.getUserDAO();
        userAddressDAO = daoFactory.getUserAddressDAO();
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
            serviceResult.setResultMessage(VALIDATE_FAILED);
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
            serviceResult.setResultMessage(VALIDATE_FAILED);
        }
    }

    @Override
    public void userInformation(int userId, ServiceResult serviceResult) throws ServiceException {
        try {
            User userData = userDAO.getUserInfo(userId);

            if(userData == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(userData);
            }
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Getting user information is failed!", e);
        }
    }

    @Override
    public void getAllUserAddresses(int userId, ServiceResult serviceResult) throws ServiceException {
        List<UserAddress> userAddresses = null;

        try {
            userAddresses = userAddressDAO.getAllUserAddresses(userId);

            if(userAddresses == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(userAddresses);
            }

        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Getting user addresses is failed!", e);
        }
    }

    @Override
    public void changeLocale(int userId, UserLocale newUserLocale, ServiceResult serviceResult) throws
                                                                                                ServiceException {
        try {
            userDAO.changeLocale(userId, newUserLocale);
            serviceResult.setResultOperation(true);
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Updating locale is failed!", e);
        }
    }

    @Override
    public void addAddress(UserAddress address, ServiceResult serviceResult) throws ServiceException {
        try {
            if(userAddressValidator.validate(address)) {
                userAddressDAO.add(address);
                serviceResult.setResultOperation(true);
            } else {
                serviceResult.setResultOperation(false);
            }
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Adding address is failed!", e);
        }
    }
}
