package by.training.zorich.service.logic;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.service.exception.ServiceException;

public interface SubscriptionService {
    void getAllSubscriptionTypes(ServiceResult serviceResult) throws ServiceException;
}
