package by.training.zorich.dal.sql_executor.impl.result_handler;

import by.training.zorich.bean.Payment;
import by.training.zorich.dal.sql_executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentResultHandler implements ResultHandler<Payment> {

    @Override
    public Payment handle(ResultSet resultSet) throws SQLException {
        Payment payment = new Payment();

        payment.setId(resultSet.getInt("idPayment"));
        payment.setAmount(resultSet.getDouble("amount"));

        boolean payStatus = false;

        if(resultSet.getInt("payStatus") != 0) {
            payStatus = true;
        }

        payment.setPayStatus(payStatus);

        return payment;
    }
}
