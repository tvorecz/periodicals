package by.training.zorich.dal.dao;

import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.bean.UserAddress;
import by.training.zorich.bean.UserSubscription;
import by.training.zorich.dal.exception.DAOException;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionDAO {
    void subscribeTransactionaly(UserSubscription userSubscription) throws DAOException;

    void subscribeTransactionaly(List<UserSubscription> userSubscriptions) throws DAOException;

    List<UserSubscription> getAllSubscriptions(int idUser, int beginOfRange, int countOfRecords) throws DAOException;

    List<UserSubscription> getAllSubscriptions(int idPayment) throws DAOException;
}
