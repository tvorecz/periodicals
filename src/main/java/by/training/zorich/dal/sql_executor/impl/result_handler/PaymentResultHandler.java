package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.Payment;
import by.training.zorich.bean.PaymentCharacteristic;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentResultHandler implements ResultHandler<Payment> {

    @Override
    public Payment handle(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();

        payment.setId(resultSet.getInt(PaymentCharacteristic.ID.getName()));
        payment.setAmount(resultSet.getDouble(PaymentCharacteristic.AMOUNT.getName()));
        payment.setPayStatus(resultSet.getBoolean(PaymentCharacteristic.PAY_STATUS.getName()));

        return payment;
    }
}
