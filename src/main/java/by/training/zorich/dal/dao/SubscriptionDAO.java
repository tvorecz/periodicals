package by.training.zorich.dal.dao;

import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.bean.UserAddress;
import by.training.zorich.bean.UserSubscription;
import by.training.zorich.dal.exception.DAOException;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionDAO {
    void subscribeTransactionaly(int idPayment, SubscriptionVariant subscriptionVariant,
                                 UserAddress address,
                                 LocalDate begin,
                                 LocalDate end) throws DAOException;

    void subscribeTransactionaly(int idPayment, List<SubscriptionVariant> subscriptionVariants,
                                 UserAddress address,
                                 List<LocalDate> begins, List<LocalDate> ends) throws DAOException;

    List<UserSubscription> getAllSubscriptions(int idUser, int beginOfRange, int countOfRecords) throws DAOException;
}
