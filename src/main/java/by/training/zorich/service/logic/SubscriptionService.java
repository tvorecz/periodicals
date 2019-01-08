package by.training.zorich.service.logic;

import by.training.zorich.bean.Periodical;
import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.bean.UserSubscription;
import by.training.zorich.service.exception.ServiceException;

import java.util.List;

public interface SubscriptionService {
    void addSubscriptionVariants(List<SubscriptionVariant> subscriptionVariants, ServiceResult serviceResult) throws
                                                                                                              ServiceException;
    void subscribe(List<UserSubscription> userSubscriptions, ServiceResult serviceResult) throws ServiceException;

    void getAllSubscriptionsForPayment(int idPayment, ServiceResult serviceResult) throws ServiceException;

    void getAllSubscriptionVariantsForPeriodical(Periodical periodical, ServiceResult serviceResult) throws ServiceException;

    void getSubscriptionVariantsByIds(List<Integer> subscriptionVariantsIds, ServiceResult serviceResult) throws ServiceException;

    void getAllSubscriptionTypes(ServiceResult serviceResult) throws ServiceException;
}
