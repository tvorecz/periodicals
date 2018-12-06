package by.training.zorich.service.factory;

import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.UserService;

public interface ServiceFactory {
    void init(DAOFactory daoFactory) throws ServiceException;

    UserService getUserService();
}
