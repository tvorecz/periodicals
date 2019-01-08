package by.training.zorich.service.logic;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.service.exception.ServiceException;

public interface UserService {
    void register(User user, ServiceResult serviceResult) throws ServiceException;

    void authenticate(User user, ServiceResult serviceResult) throws ServiceException;

    void userInformation(int userId, ServiceResult serviceResult) throws ServiceException;

    void getAllUserAddresses(int userId, ServiceResult serviceResult) throws ServiceException;

    void changeLocale(int userId, UserLocale newUserLocale, ServiceResult serviceResult) throws ServiceException;
}
