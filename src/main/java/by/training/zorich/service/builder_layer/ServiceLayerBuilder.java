package by.training.zorich.service.builder_layer;

import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;

public interface ServiceLayerBuilder {
    ServiceFactory build() throws ServiceException;
}
