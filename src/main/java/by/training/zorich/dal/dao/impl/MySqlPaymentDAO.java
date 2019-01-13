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

import java.util.Locale;

public class MySqlPaymentDAO extends CommonDAO<Object> implements PaymentDAO {
    private static final String QUERY_SELECT_LAST_PAYMENT_ID = "SELECT last_insert_id()";
    private static final String QUERY_CREATE_PAYMENT = "INSERT INTO payments (amount, payStatus) values(%1$f, %2$d)";
    private static final String QUERY_SELECT_PAYMENT_BY_ID = "SELECT idPayment, amount, payStatus FROM payments WHERE" +
                                                             " idPayment = %1$d";
    private static final String QUERY_SELECT_PAYMENT_BY_USER_ID = "SELECT *\n" +
                                                                  "\t\tFROM payments\n" +
                                                                  "\t\t\tJOIN user_subscriptions ON payments" +
                                                                  ".idPayment = user_subscriptions.idPayment\n" +
                                                                  "\t\t\tJOIN user_addresses ON user_addresses" +
                                                                  ".idAddress = user_subscriptions.idAddress\n" +
                                                                  "\t\t\tJOIN users ON user_addresses.idUser = users" +
                                                                  ".idUser\n" +
                                                                  "\t\t\t\n" +
                                                                  "\t\t\tWHERE payments.idPayment = %1$d AND users" +
                                                                  ".idUser = %2$d";

    public MySqlPaymentDAO(DataSourceConnector connector,
                           TransactionManager transactionManager,
                           SQLExecutor sqlExecutor,
                           ResultHandlerRepository resultHandlerRepository) {
        super(connector, transactionManager, sqlExecutor, resultHandlerRepository);
    }

    @Override
    public void createPaymentTransactionaly(double totalCost) throws DAOException {
        String query = String.format(Locale.US, QUERY_CREATE_PAYMENT, totalCost, 0);
        super.executeUpdateDataSource(query, TransactionStatus.ON);
    }

    @Override
    public Integer getLastInsertedPaymentIdTransactionaly() throws DAOException {
        return (Integer) super.executeSelectFromDataSource(QUERY_SELECT_LAST_PAYMENT_ID,
                                                           HandlerType.SCALAR,
                                                           TransactionStatus.ON);
    }

    @Override
    public Payment getPaymentById(int idPayment) throws DAOException {
        String query = String.format(QUERY_SELECT_PAYMENT_BY_ID, idPayment);

        return (Payment) super.executeSelectFromDataSource(query,
                                                           HandlerType.SELECT_PAYMENT_BY_ID,
                                                           TransactionStatus.OFF);
    }

    @Override
    public Boolean checkPaymentBelongingToUser(int idPayment, int idUser) throws DAOException {
        String query = String.format(QUERY_SELECT_PAYMENT_BY_USER_ID, idPayment, idUser);

        return (Boolean) super.executeSelectFromDataSource(query, HandlerType.VALIDATE_HANDLER, TransactionStatus.OFF);
    }
}
