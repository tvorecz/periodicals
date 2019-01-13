/**
 * Validate access to user payment.
 * If this payment doesn't belong to this user than returns false.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */
package by.training.zorich.service.validator.impl.subscription_validator;

import by.training.zorich.bean.Payment;
import by.training.zorich.dal.dao.PaymentDAO;
import by.training.zorich.dal.exception.DAOException;
import by.training.zorich.dal.factory.impl.MySqlDAOFactory;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.validator.Validator;

public class PaymentBeloningToUserValidator implements Validator<Payment> {
    private final static PaymentDAO PAYMENT_DAO = MySqlDAOFactory.getInstance().getPaymentDAO();

    @Override
    public boolean validate(Payment objectForValidation) throws ServiceException {
        return validatePaymentBeloningToUser(objectForValidation.getId(), objectForValidation.getUserId());
    }

    private boolean validatePaymentBeloningToUser(int idPayment, int idUser) throws ServiceException {
        try {
            return !PAYMENT_DAO.checkPaymentBelongingToUser(idPayment, idUser);
        } catch (DAOException e) {
            throw new ServiceException("Checking belonging payment to user id failed!", e);
        }
    }
}
