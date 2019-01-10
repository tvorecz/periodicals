package by.training.zorich.service.util;

import by.training.zorich.bean.SubscriptionType;

import java.time.LocalDate;

public interface SubscriptionLocalDateCalculator {
    LocalDate calculateStartSubscriptions(SubscriptionType subscriptionType);
    LocalDate calculateEndSubscription(LocalDate start, SubscriptionType subscriptionType);
}
