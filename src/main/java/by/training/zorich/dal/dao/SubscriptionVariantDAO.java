package by.training.zorich.dal.dao;

import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.dal.exception.DAOException;

public interface SubscriptionVariantDAO {
    void addSubscriptionVariant(SubscriptionVariant subscriptionVariant) throws DAOException;
    void deleteSubscriptionVariant(SubscriptionVariant subscriptionVariant) throws DAOException;
    SubscriptionVariant getSubscriptionVariantById(int idSubscriptionVariant) throws DAOException;
}
