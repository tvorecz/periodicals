package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.*;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSubscriptionHandler implements ResultHandler<List<UserSubscription>> {


    @Override
    public List<UserSubscription> handle(ResultSet resultSet) throws SQLException {
        List<UserSubscription> result = new ArrayList<>();

        while (resultSet.next()) {
            UserSubscription userSubscription =  new UserSubscription();
            userSubscription.setId(resultSet.getInt(UserSubscriptionCharacteristic.ID.getName()));
            userSubscription.setDateBegin(resultSet.getDate(UserSubscriptionCharacteristic.DATE_BEGIN.getName()).toLocalDate());
            userSubscription.setDateEnd(resultSet.getDate(UserSubscriptionCharacteristic.DATE_END.getName()).toLocalDate());


            Payment payment = new Payment();
            payment.setId(resultSet.getInt(PaymentCharacteristic.ID.getName()));
            payment.setAmount(resultSet.getInt(PaymentCharacteristic.AMOUNT.getName()));
            payment.setPayStatus(resultSet.getBoolean(PaymentCharacteristic.PAY_STATUS.getName()));
            userSubscription.setPayment(payment);


            SubscriptionVariant subscriptionVariant = new SubscriptionVariant();
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

            userSubscription.setSubscriptionVariant(subscriptionVariant);

            UserAddress userAddress = new UserAddress();
            userAddress.setIdUser(resultSet.getInt(UserAddressCharacteristic.ID_USER.getName()));
            userAddress.setIdAdress(resultSet.getInt(UserAddressCharacteristic.ID.getName()));
            userAddress.setAddress(resultSet.getString(UserAddressCharacteristic.ADDRESS.getName()));

            userSubscription.setUserAddress(userAddress);


            result.add(userSubscription);
        }

        return result;
    }
}
