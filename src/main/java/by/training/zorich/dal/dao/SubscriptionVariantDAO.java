package by.training.zorich.dal.dao;

import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface SubscriptionVariantDAO {
    void addSubscriptionVariant(SubscriptionVariant subscriptionVariant) throws DAOException;

    void addSubscriptionVariantsTransactionaly(List<SubscriptionVariant> subscriptionVariants) throws DAOException;

    List<SubscriptionVariant> getAllSubscriptionVariantsForPeriodical(int idPeriodical) throws DAOException;

    void deleteSubscriptionVariant(SubscriptionVariant subscriptionVariant) throws DAOException;

    void deleteSubscriptionVariants(List<SubscriptionVariant> subscriptionVariants) throws DAOException;

    SubscriptionVariant getSubscriptionVariantById(int idSubscriptionVariant) throws DAOException;

    List<SubscriptionVariant> getSubscriptionVariantsByIdsTransactionaly(List<Integer> subscriptionVariantsIds) throws DAOException;
}
