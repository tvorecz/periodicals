package by.training.zorich.service.logic.impl;

import by.training.zorich.bean.*;
import by.training.zorich.dal.dao.PaymentDAO;
import by.training.zorich.dal.dao.SubscriptionDAO;
import by.training.zorich.dal.dao.SubscriptionTypeDAO;
import by.training.zorich.dal.dao.SubscriptionVariantDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.DAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.logic.SubscriptionService;
import by.training.zorich.service.util.SubscriptionLocalDateCalculator;
import by.training.zorich.service.util.impl.SubscriptionLocalDateCalculatorImpl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionTypeDAO subscriptionTypeDAO;
    private SubscriptionVariantDAO subscriptionVariantDAO;
    private SubscriptionDAO subscriptionDAO;
    private PaymentDAO paymentDAO;
    private SubscriptionLocalDateCalculator subscriptionLocalDateCalculator;

    public SubscriptionServiceImpl(DAOFactory daoFactory) {
        subscriptionTypeDAO = daoFactory.getSubscriptionTypeDAO();
        subscriptionVariantDAO = daoFactory.getSubscriptionVariantDAO();
        subscriptionDAO = daoFactory.getSubscriptionDAO();
        paymentDAO = daoFactory.getPaymentDAO();
        subscriptionLocalDateCalculator = new SubscriptionLocalDateCalculatorImpl();
    }

    @Override
    public void addSubscriptionVariants(List<SubscriptionVariant> subscriptionVariants, ServiceResult serviceResult) throws ServiceException {
        try {
            subscriptionVariantDAO.addSubscriptionVariantsTransactionaly(subscriptionVariants);
            serviceResult.setResultOperation(true);
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Adding subscription variants is failed!", e);
        }
    }

    @Override
    public void subscribe(List<UserSubscription> userSubscriptions, ServiceResult serviceResult) throws
                                                                                                 ServiceException {
        try {
            Payment commonPayment = userSubscriptions.get(0).getPayment();
            double totalCost = commonPayment.getAmount();
            paymentDAO.createPaymentTransactionaly(totalCost);
            Integer idNewPayment = paymentDAO.getLastInsertedPaymentIdTransactionaly();

            commonPayment.setId(idNewPayment);
            commonPayment.setPayStatus(false);

            calculateActualDatesToUserSubscriptions(userSubscriptions);

            if(userSubscriptions.size() == 1) {
                subscriptionDAO.subscribeTransactionaly(userSubscriptions.get(0));
            } else {
                subscriptionDAO.subscribeTransactionaly(userSubscriptions);
            }

            serviceResult.setResultOperation(true);
            serviceResult.setResultObject(idNewPayment);

        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Subscribing is failed!", e);
        }
    }

    @Override
    public void getAllSubscriptionsForPayment(int idPayment, ServiceResult serviceResult) throws ServiceException {
        try {
            List<UserSubscription> userSubscriptions = subscriptionDAO.getAllSubscriptions(idPayment);

            serviceResult.setResultOperation(true);
            serviceResult.setResultObject(userSubscriptions);
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Subscribing is failed!", e);
        }
    }

    @Override
    public void getAllSubscriptionVariantsForPeriodical(Periodical periodical, ServiceResult serviceResult) throws
                                                                                                       ServiceException {
        try {
            List<SubscriptionVariant> subscriptionVariants = subscriptionVariantDAO.getAllSubscriptionVariantsForPeriodical(periodical.getId());

            calculateActualCostForSubscriptionVariants(periodical, subscriptionVariants);

            serviceResult.setResultOperation(true);
            serviceResult.setResultObject(subscriptionVariants);
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Getting subscription variants for periodical is failed!", e);
        }
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

    @Override
    public void getSubscriptionVariantsByIds(List<Integer> subscriptionVariantsIds, ServiceResult serviceResult) throws
                                                                                                                 ServiceException {
        try {
            List<SubscriptionVariant> subscriptionVariants = subscriptionVariantDAO.getSubscriptionVariantsByIds(subscriptionVariantsIds);

            if(subscriptionVariants == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(subscriptionVariants);
            }
        } catch (DAOException e) {
            serviceResult.setResultOperation(false);
            throw new ServiceException("Getting subscription variants is failed!", e);
        }
    }

    private void calculateActualCostForSubscriptionVariants(Periodical periodical, List<SubscriptionVariant> subscriptionVariants) {
        Iterator<SubscriptionVariant> subscriptionVariantIterator = subscriptionVariants.iterator();

        while (subscriptionVariantIterator.hasNext()) {
            SubscriptionVariant subscriptionVariant = subscriptionVariantIterator.next();
            subscriptionVariant.setPeriodical(periodical);
            subscriptionVariant.calculateActualCost();
        }
    }

    private void calculateActualDatesToUserSubscriptions(List<UserSubscription> userSubscriptions) {
        for (UserSubscription userSubscription : userSubscriptions) {
            LocalDate dateBegin = subscriptionLocalDateCalculator.calculateStartSubscriptions(userSubscription.getSubscriptionVariant().getSubscriptionType());
            LocalDate dateEnd = subscriptionLocalDateCalculator.calculateEndSunscription(dateBegin, userSubscription.getSubscriptionVariant().getSubscriptionType());
            userSubscription.setDateBegin(dateBegin);
            userSubscription.setDateEnd(dateEnd);
        }
    }
}
