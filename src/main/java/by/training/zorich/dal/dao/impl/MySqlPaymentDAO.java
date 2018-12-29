package by.training.zorich.dal.dao.impl;

import by.training.zorich.bean.Payment;
import by.training.zorich.dal.connector.DataSourceConnector;
import by.training.zorich.dal.connector.TransactionManager;
import by.training.zorich.dal.dao.PaymentDAO;
import by.training.zorich.dal.dao.TransactionStatus;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.sql_executor.HandlerType;
import by.training.zorich.dal.sql_executor.ResultHandlerRepository;
import by.training.zorich.dal.sql_executor.SQLExecutor;

public class MySqlPaymentDAO extends CommonDAO<Object> implements PaymentDAO {
    private static final String QUERY_SELECT_LAST_PAYMENT_ID = "SELECT last_insert_id()";
    private static final String QUERY_CREATE_PAYMENT = "INSERT INTO payments (amount, payStatus) values(%1$f, %2$d)";
    private static final String QUERY_SELECT_PAYMENT_BY_ID = "SELECT idPayment, amount, payStatus FROM payments WHERE idPayment = %1$d";

    public MySqlPaymentDAO(DataSourceConnector connector,
                           TransactionManager transactionManager,
                           SQLExecutor sqlExecutor,
                           ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void createPaymentTransactionaly(Payment payment) throws DAOException {
        String query = String.format(QUERY_CREATE_PAYMENT, payment.getAmount(), 0);
        super.executeUpdateDataSource(query, TransactionStatus.ON);
    }

    @Override
    public Integer getLastInsertedPaymentIdTransactionaly() throws DAOException {
        return (Integer) super.executeSelectFromDataSource(QUERY_SELECT_LAST_PAYMENT_ID, HandlerType.LAST_INSERTED_PAYMENT_ID, TransactionStatus.ON);
    }

    @Override
    public Payment getPaymentById(int idPayment) throws DAOException {
        String query = String.format(QUERY_SELECT_PAYMENT_BY_ID, idPayment);

        return (Payment) super.executeSelectFromDataSource(query, HandlerType.SELECT_PAYMENT_BY_ID, TransactionStatus.OFF);
    }
}
