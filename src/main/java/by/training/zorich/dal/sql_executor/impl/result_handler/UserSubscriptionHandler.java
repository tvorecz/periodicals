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
        resultSet.getRowId(0);
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


            SubsciptionVariant subsciptionVariant = new SubsciptionVariant();
            subsciptionVariant.setId(resultSet.getInt(SubscriptionVariantCharacteristic.ID.getName()));
            subsciptionVariant.setIndex(resultSet.getString(SubscriptionVariantCharacteristic.INDEX.getName()));
            subsciptionVariant.setCost(resultSet.getDouble(SubscriptionVariantCharacteristic.COST.getName()));
            subsciptionVariant.setTypeSubscription(resultSet.getString(SubscriptionVariantCharacteristic.TYPE.getName()));

            Periodical periodical = new Periodical();
            periodical.setId(resultSet.getInt(PeriodicalCharacteristic.ID.getName()));
            periodical.setName(resultSet.getString(PeriodicalCharacteristic.NAME.getName()));
            periodical.setAnnotation(resultSet.getString(PeriodicalCharacteristic.ANNOTATION.getName()));
            periodical.setImagePath(resultSet.getString(PeriodicalCharacteristic.IMAGE.getName()));
            periodical.setTheme(resultSet.getString(PeriodicalCharacteristic.THEME.getName()));
            periodical.setType(resultSet.getString(PeriodicalCharacteristic.TYPE.getName()));
            periodical.setPeriodicityInMonth(resultSet.getInt(PeriodicalCharacteristic.PERIODICITY.getName()));
            subsciptionVariant.setPeriodical(periodical);

            userSubscription.setSubsciptionVariant(subsciptionVariant);


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
