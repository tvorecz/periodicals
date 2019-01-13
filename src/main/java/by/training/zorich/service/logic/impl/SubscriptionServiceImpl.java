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
import by.training.zorich.service.validator.Validator;
import by.training.zorich.service.validator.impl.subscription_validator.PaymentBeloningToUserValidator;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionTypeDAO subscriptionTypeDAO;
    private final SubscriptionVariantDAO subscriptionVariantDAO;
    private final SubscriptionDAO subscriptionDAO;
    private final PaymentDAO paymentDAO;
    private final SubscriptionLocalDateCalculator subscriptionLocalDateCalculator;
    private final Validator<Payment> paymentValidator;

    public SubscriptionServiceImpl(DAOFactory daoFactory) {
        subscriptionTypeDAO = daoFactory.getSubscriptionTypeDAO();
        subscriptionVariantDAO = daoFactory.getSubscriptionVariantDAO();
        subscriptionDAO = daoFactory.getSubscriptionDAO();
        paymentDAO = daoFactory.getPaymentDAO();
        subscriptionLocalDateCalculator = new SubscriptionLocalDateCalculatorImpl();
        paymentValidator = new PaymentBeloningToUserValidator();
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

            calculateActualDatesForUserSubscriptions(userSubscriptions);

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
    public void getAllSubscriptionsForPayment(int idPayment, int idUser, ServiceResult serviceResult) throws ServiceException {
        try {
            Payment payment = new Payment();
            payment.setId(idPayment);
            payment.setUserId(idUser);

            if(paymentValidator.validate(payment)) {
                List<UserSubscription> userSubscriptions = subscriptionDAO.getAllSubscriptions(idPayment);

                serviceResult.setResultOperation(true);
                serviceResult.setResultObject(userSubscriptions);
            } else {
                serviceResult.setResultOperation(false);
            }
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
            List<SubscriptionVariant> subscriptionVariants = subscriptionVariantDAO.getSubscriptionVariantsByIdsTransactionaly(subscriptionVariantsIds);

            if(subscriptionVariants == null) {
                serviceResult.setResultOperation(false);
            } else {
                serviceResult.setResultOperation(true);
                calculateActualCostForSubscriptionVariants(subscriptionVariants);
                calculateExpectedBeginsOfSubscription(subscriptionVariants);
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

    private void calculateActualCostForSubscriptionVariants(List<SubscriptionVariant> subscriptionVariants) {
        Iterator<SubscriptionVariant> subscriptionVariantIterator = subscriptionVariants.iterator();

        while (subscriptionVariantIterator.hasNext()) {
            SubscriptionVariant subscriptionVariant = subscriptionVariantIterator.next();
            subscriptionVariant.calculateActualCost();
        }
    }

    private void calculateActualDatesForUserSubscriptions(List<UserSubscription> userSubscriptions) throws DAOException {
        Iterator<UserSubscription> userSubscriptionIterator = userSubscriptions.iterator();

        while (userSubscriptionIterator.hasNext()) {
            UserSubscription userSubscription = userSubscriptionIterator.next();
            SubscriptionType subscriptionType = subscriptionTypeDAO.getSubscriptionTypeByIdTransactionaly(userSubscription.getSubscriptionVariant().getSubscriptionType().getId());

            LocalDate dateBegin = subscriptionLocalDateCalculator.calculateStartSubscriptions(subscriptionType);
            LocalDate dateEnd = subscriptionLocalDateCalculator.calculateEndSubscription(dateBegin, subscriptionType);

            userSubscription.setDateBegin(dateBegin);
            userSubscription.setDateEnd(dateEnd);
        }
    }

    private void calculateExpectedBeginsOfSubscription(List<SubscriptionVariant> subscriptionVariants) {
        Iterator<SubscriptionVariant> userSubscriptions = subscriptionVariants.iterator();

        while (userSubscriptions.hasNext()) {
            SubscriptionVariant subscriptionVariant = userSubscriptions.next();
            LocalDate expectedBeginOfSubscription = subscriptionLocalDateCalculator.calculateStartSubscriptions(subscriptionVariant.getSubscriptionType());
            subscriptionVariant.setExpectedBeginOfSubscription(expectedBeginOfSubscription);
        }
    }
}
