package by.training.zorich.service.logic.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.SubscriptionType;
import by.training.zorich.dal.dao.SubscriptionTypeDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.SubscriptionService;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionTypeDAO subscriptionTypeDAO;

    public SubscriptionServiceImpl(DAOFactory daoFactory) {
        subscriptionTypeDAO = daoFactory.getSubscriptionTypeDAO();
    }

    @Override
    public void getAllSubscriptionTypes(ServiceResult serviceResult) throws ServiceException {
        try {
            List<SubscriptionType> subscriptionTypeList = subscriptionTypeDAO.getAllSubscriptionTypes();

            if(subscriptionTypeList == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(subscriptionTypeList);
            }

        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Getting subscription types is failed!", e);
        }
    }
}
