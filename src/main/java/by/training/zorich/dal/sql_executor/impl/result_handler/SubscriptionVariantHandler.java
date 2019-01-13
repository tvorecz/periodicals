package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.*;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionVariantHandler implements ResultHandler<SubscriptionVariant> {
    @Override
    public SubscriptionVariant handle(ResultSet resultSet) throws SQLException {
        SubscriptionVariant subscriptionVariant = null;

        if (resultSet.next()) {
            subscriptionVariant = new SubscriptionVariant();
            subscriptionVariant.setId(resultSet.getInt(SubscriptionVariantCharacteristic.ID.getName()));
            subscriptionVariant.setIndex(resultSet.getString(SubscriptionVariantCharacteristic.INDEX.getName()));
            subscriptionVariant.setCostForIssue(resultSet.getDouble(SubscriptionVariantCharacteristic.COST.getName()));

            SubscriptionType subscriptionType = new SubscriptionType();
            subscriptionType.setId(resultSet.getInt(SubscriptionTypeCharacteristic.ID.getName()));
            subscriptionType.setName(resultSet.getString(SubscriptionTypeCharacteristic.NAME.getName()));
            subscriptionType.setMonthAmount(resultSet.getInt(SubscriptionTypeCharacteristic.MONTH_AMOUNT.getName()));
            subscriptionVariant.setSubscriptionType(subscriptionType);

            Periodical periodical = new Periodical();
            periodical.setId(resultSet.getInt(PeriodicalCharacteristic.ID.getName()));
            periodical.setName(resultSet.getString(PeriodicalCharacteristic.NAME.getName()));
            periodical.setAnnotation(resultSet.getString(PeriodicalCharacteristic.ANNOTATION.getName()));
            periodical.setImagePath(resultSet.getString(PeriodicalCharacteristic.IMAGE.getName()));
            periodical.setPeriodicityInMonth(resultSet.getInt(PeriodicalCharacteristic.PERIODICITY.getName()));

            PeriodicalType periodicalType = new PeriodicalType();
            periodicalType.setId(resultSet.getInt(PeriodicalTypeCharacteristic.ID.getName()));
            periodicalType.setName(resultSet.getString(PeriodicalTypeCharacteristic.NAME.getName()));
            periodical.setType(periodicalType);

            PeriodicalTheme periodicalTheme = new PeriodicalTheme();
            periodicalTheme.setId(resultSet.getInt(PeriodicalThemeCharacteristic.ID.getName()));
            periodicalTheme.setName(resultSet.getString(PeriodicalThemeCharacteristic.NAME.getName()));
            periodical.setTheme(periodicalTheme);

            subscriptionVariant.setPeriodical(periodical);
        }

        return subscriptionVariant;
    }
}
