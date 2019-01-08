package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.*;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleSubscriptionVariantHandler implements ResultHandler<List<SubscriptionVariant>> {

    @Override
    public List<SubscriptionVariant> handle(ResultSet resultSet) throws SQLException {
        List<SubscriptionVariant> subscriptionVariants = new ArrayList<>();

        while (resultSet.next()) {
            SubscriptionVariant subscriptionVariant = new SubscriptionVariant();
            subscriptionVariant.setId(resultSet.getInt(SubscriptionVariantCharacteristic.ID.getName()));
            subscriptionVariant.setIndex(resultSet.getString(SubscriptionVariantCharacteristic.INDEX.getName()));
            subscriptionVariant.setCostForIssue(resultSet.getDouble(SubscriptionVariantCharacteristic.COST.getName()));

            SubscriptionType subscriptionType = new SubscriptionType();
            subscriptionType.setId(resultSet.getInt(SubscriptionTypeCharacteristic.ID.getName()));
            subscriptionType.setName(resultSet.getString(SubscriptionTypeCharacteristic.NAME.getName()));
            subscriptionType.setMonthAmount(resultSet.getInt(SubscriptionTypeCharacteristic.MONTH_AMOUNT.getName()));
            subscriptionVariant.setSubscriptionType(subscriptionType);

            subscriptionVariants.add(subscriptionVariant);
        }

        return subscriptionVariants;
    }
}
