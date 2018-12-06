package by.training.zorich.service.builder_layer.impl;

import by.training.zorich.dal.builder_layer.impl.DalSQLiteBuilder;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.builder_layer.ServiceLayerBuilder;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import by.training.zorich.service.factory.impl.ServiceFactoryImpl;

public class ServiceLayerBuilderImpl implements ServiceLayerBuilder {

    @Override
    public ServiceFactory build() throws ServiceException {
        DAOFactory factory = null;

        try {
            factory = new DalSQLiteBuilder().build();
        } catch (DAOException e) {
            throw new ServiceException("Error during building data access layer.", e);
        }

        ServiceFactory serviceFactory = ServiceFactoryImpl.getInstance();

        serviceFactory.init(factory);

        return serviceFactory;
    }
}
