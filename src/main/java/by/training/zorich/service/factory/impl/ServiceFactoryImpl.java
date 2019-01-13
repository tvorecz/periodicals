/**
 * Provides access to service-classes. Using pattern singleton.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.service.factory.impl;

import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import by.training.zorich.service.logic.PeriodicalService;
import by.training.zorich.service.logic.SubscriptionService;
import by.training.zorich.service.logic.UserService;
import by.training.zorich.service.logic.impl.PeriodicalServiceImpl;
import by.training.zorich.service.logic.impl.SubscriptionServiceImpl;
import by.training.zorich.service.logic.impl.UserServiceImpl;

public class ServiceFactoryImpl implements ServiceFactory {
    private static UserService userService;
    private static PeriodicalService periodicalService;
    private static SubscriptionService subscriptionService;

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
        periodicalService = new PeriodicalServiceImpl(daoFactory);
        subscriptionService = new SubscriptionServiceImpl(daoFactory);
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public SubscriptionService getSubscriptionService() {
        return subscriptionService;
    }

    @Override
    public PeriodicalService getPeriodicalService() {
        return periodicalService;
    }
}
