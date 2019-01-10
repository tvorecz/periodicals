package by.training.zorich.service.util.impl;

import by.training.zorich.bean.SubscriptionType;
import by.training.zorich.service.util.SubscriptionLocalDateCalculator;

import java.time.LocalDate;

public class SubscriptionLocalDateCalculatorImpl implements SubscriptionLocalDateCalculator {

    @Override
    public LocalDate calculateStartSubscriptions(SubscriptionType subscriptionType) {
        SubscriptionCase subscriptionCase = determineSubscriptionCase(subscriptionType);

        LocalDate currentLocalDate = LocalDate.now();

        switch (subscriptionCase) {
            case HALF_YEAR:
                return calculate(6);

            case QUARTER:
                return calculate(3);

            case MONTH:
                LocalDate beginOfCurrentMonth = LocalDate.of(currentLocalDate.getYear(), currentLocalDate.getMonth(), 1);
                return beginOfCurrentMonth.plusMonths(1);
        }

        return null;
    }

    @Override
    public LocalDate calculateEndSubscription(LocalDate start, SubscriptionType subscriptionType) {
        SubscriptionCase subscriptionCase = determineSubscriptionCase(subscriptionType);

        switch (subscriptionCase) {
            case MONTH:
                return start.plusMonths(1);
            case QUARTER:
                return start.plusMonths(3);
            case HALF_YEAR:
                return start.plusMonths(6);
        }

        return null;
    }

    private SubscriptionCase determineSubscriptionCase(SubscriptionType subscriptionType) {
        if (subscriptionType.getMonthAmount() == 1) {
            return SubscriptionCase.MONTH;
        } else if(subscriptionType.getMonthAmount() == 3) {
            return SubscriptionCase.QUARTER;
        } else {
            return SubscriptionCase.HALF_YEAR;
        }
    }

    private LocalDate calculate(int countOfMonth) {
        LocalDate currentLocalDate = LocalDate.now();

        int counter = 1;

        while (counter <= currentLocalDate.getMonthValue()) {
            counter += countOfMonth;
        }

        LocalDate beginOfCurrentMonth = LocalDate.of(currentLocalDate.getYear(), currentLocalDate.getMonth(), 1);

        LocalDate result = beginOfCurrentMonth.plusMonths(counter - currentLocalDate.getMonthValue());

        return result;
    }
}

enum SubscriptionCase{
    MONTH, QUARTER, HALF_YEAR
}
