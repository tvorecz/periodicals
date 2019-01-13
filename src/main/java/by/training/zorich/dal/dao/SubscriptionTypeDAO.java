package by.training.zorich.dal.dao;

import by.training.zorich.bean.SubscriptionType;
import by.training.zorich.dal.exception.DAOException;

import java.util.List;

public interface SubscriptionTypeDAO {
    List<SubscriptionType> getAllSubscriptionTypes() throws DAOException;

    SubscriptionType getSubscriptionTypeByIdTransactionaly(int id) throws DAOException;
}
