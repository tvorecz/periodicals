package by.training.zorich.dal.dao;

import by.training.zorich.bean.Payment;
import by.training.zorich.bean.SubsciptionVariant;
import by.training.zorich.bean.UserAddress;

import java.util.List;

public interface SubscriptionDAO {
    Payment subscribeTransactionaly(SubsciptionVariant subscriptionVariant, UserAddress address);

    Payment subscribeTransactionaly(List<SubsciptionVariant> subscriptionVariants, UserAddress address);
}
