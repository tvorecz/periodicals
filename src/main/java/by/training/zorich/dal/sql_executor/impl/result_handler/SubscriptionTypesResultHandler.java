package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.SubscriptionType;
import by.training.zorich.bean.SubscriptionTypeCharacteristic;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionTypesResultHandler implements ResultHandler<List<SubscriptionType>> {
    @Override
    public List<SubscriptionType> handle(ResultSet resultSet) throws SQLException {
        List<SubscriptionType> result = new ArrayList<>();

        while (resultSet.next()) {
            SubscriptionType subscriptionType = new SubscriptionType();

            subscriptionType.setId(resultSet.getInt(SubscriptionTypeCharacteristic.ID.getName()));
            subscriptionType.setName(resultSet.getString(SubscriptionTypeCharacteristic.NAME.getName()));
            subscriptionType.setMonthAmount(resultSet.getInt(SubscriptionTypeCharacteristic.MONTH_AMOUNT.getName()));

            result.add(subscriptionType);
        }

        return result;
    }
}
