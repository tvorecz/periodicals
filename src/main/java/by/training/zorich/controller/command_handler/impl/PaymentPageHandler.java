/**
 * Handler for creating page with information about payment.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.UserSubscription;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.controller.SessionAttribute;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PaymentPageHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(PaymentPageHandler.class);
    private final static String PAYMENT_PAGE = "/subscriber/payment/";
    private final static String ERROR_404 = "/error404";
    private final static String EMPTY = "";
    private final static String USER_SUBSCRIPTIONS = "userSubscriptions";
    private final static String PAYMENT = "payment";
    private final ServiceFactory serviceFactory;

    public PaymentPageHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        HttpSession httpSession = request.getSession();
        Integer userId = (int) httpSession.getAttribute(SessionAttribute.CURRENT_USER_ID.getName());


        if (userId != null) {
            String paymentId = request.getRequestURI().replace(PAYMENT_PAGE, EMPTY);

            if (paymentId == null) {
                response.sendRedirect(ERROR_404);
            } else {
                Integer idPayment = Integer.parseInt(paymentId);

                try {
                    ServiceResult serviceResult = new ServiceResult();
                    serviceFactory.getSubscriptionService().getAllSubscriptionsForPayment(idPayment,
                                                                                          userId,
                                                                                          serviceResult);

                    if (serviceResult.isDone()) {
                        List<UserSubscription> userSubscriptions =
                                (List<UserSubscription>) serviceResult.getResultObject();

                        request.setAttribute(USER_SUBSCRIPTIONS, userSubscriptions);
                        request.setAttribute(PAYMENT, userSubscriptions.get(0).getPayment());
                    } else {
                        response.sendRedirect(ERROR_404);
                    }
                } catch (ServiceException e) {
                    LOGGER.error(e);
                    response.sendRedirect(ERROR_404);
                }
            }
        } else {
            response.sendRedirect(ERROR_404);
        }

    }
}
