package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.Periodical;
import by.training.zorich.bean.PeriodicalCharacteristic;
import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.bean.SubscriptionVariantCharacteristic;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionVariantHandler implements ResultHandler<SubscriptionVariant> {
    @Override
    public SubscriptionVariant handle(ResultSet resultSet) throws SQLException {
        SubscriptionVariant subscriptionVariant = new SubscriptionVariant();
        subscriptionVariant.setId(resultSet.getInt(SubscriptionVariantCharacteristic.ID.getName()));
        subscriptionVariant.setIndex(resultSet.getString(SubscriptionVariantCharacteristic.INDEX.getName()));
        subscriptionVariant.setCost(resultSet.getDouble(SubscriptionVariantCharacteristic.COST.getName()));
        subscriptionVariant.setTypeSubscription(resultSet.getString(SubscriptionVariantCharacteristic.TYPE.getName()));
        subscriptionVariant.setMonthAmount(resultSet.getInt(SubscriptionVariantCharacteristic.MONTH_AMOUNT.getName()));


        Periodical periodical = new Periodical();
        periodical.setId(resultSet.getInt(PeriodicalCharacteristic.ID.getName()));
        periodical.setName(resultSet.getString(PeriodicalCharacteristic.NAME.getName()));
        periodical.setAnnotation(resultSet.getString(PeriodicalCharacteristic.ANNOTATION.getName()));
        periodical.setImagePath(resultSet.getString(PeriodicalCharacteristic.IMAGE.getName()));
        periodical.setTheme(resultSet.getString(PeriodicalCharacteristic.THEME.getName()));
        periodical.setType(resultSet.getString(PeriodicalCharacteristic.TYPE.getName()));
        periodical.setPeriodicityInMonth(resultSet.getInt(PeriodicalCharacteristic.PERIODICITY.getName()));
        subscriptionVariant.setPeriodical(periodical);

        return subscriptionVariant;
    }
}
