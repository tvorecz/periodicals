package by.training.zorich.dal.dao;

import by.training.zorich.bean.Payment;
import by.training.zorich.dal.exception.DAOException;

public interface PaymentDAO {
    Integer getLastInsertedPaymentIdTransactionaly() throws DAOException;

    Payment getPaymentById(int idPayment) throws DAOException;

    void createPaymentTransactionaly(double totalCost) throws DAOException;
}
