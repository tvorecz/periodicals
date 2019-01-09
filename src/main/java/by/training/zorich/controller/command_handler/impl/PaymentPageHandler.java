package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.UserSubscription;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PaymentPageHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(PaymentPageHandler.class);
    private final static String PAYMENT_PAGE = "/subscriber/payment/";
    private ServiceFactory serviceFactory;

    public PaymentPageHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        String paymentId = request.getRequestURI().replace(PAYMENT_PAGE, "");

        if(paymentId == null) {
            response.sendRedirect(JspPagePath.ERROR);
        } else {
            Integer idPayment = Integer.parseInt(paymentId);

            try {
                ServiceResult serviceResult = new ServiceResult();
                serviceFactory.getSubscriptionService().getAllSubscriptionsForPayment(idPayment, serviceResult);
                List<UserSubscription> userSubscriptions = (List<UserSubscription>) serviceResult.getResultObject();

                request.setAttribute("userSubscriptions", userSubscriptions);
                request.setAttribute("payment", userSubscriptions.get(0).getPayment());

            } catch (ServiceException e) {
                LOGGER.error(e);
                response.sendRedirect(JspPagePath.ERROR);
            }
        }
    }
}
