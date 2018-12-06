package by.training.zorich.service.factory.impl;

import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import by.training.zorich.service.logic.UserService;
import by.training.zorich.service.logic.impl.UserServiceImpl;

public class ServiceFactoryImpl implements ServiceFactory {
    private static UserService userService;

    private ServiceFactoryImpl() {
    }

    private static class ServiceFactoryHelper {
        private static final ServiceFactoryImpl FACTORY = new ServiceFactoryImpl();
    }

    public static ServiceFactoryImpl getInstance() {
        return ServiceFactoryHelper.FACTORY;
    }

    @Override
    public void init(DAOFactory daoFactory) throws ServiceException {
        userService = new UserServiceImpl(daoFactory);
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
