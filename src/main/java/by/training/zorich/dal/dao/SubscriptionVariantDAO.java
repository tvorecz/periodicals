package by.training.zorich.dal.dao;

import by.training.zorich.bean.SubsciptionVariant;
import by.training.zorich.dal.exception.DAOException;

public interface SubscriptionVariantDAO {
    SubsciptionVariant getSubscriptionVariantById(int idSubscriptionVariant) throws DAOException;
}
