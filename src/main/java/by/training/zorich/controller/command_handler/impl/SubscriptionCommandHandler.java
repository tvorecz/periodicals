package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.*;
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
import java.util.ArrayList;
import java.util.List;

public class SubscriptionCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SubscriptionCommandHandler.class);
    private final static String CART_ERROR = "/subscriber/cart?message=subscribeError";
    private final static String PAYMENT_PAGE = "/subscriber/payment/%1$d?message=success";
    private ServiceFactory serviceFactory;

    public SubscriptionCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        List<UserSubscription> userSubscriptionList = handleRequest(request);

        try {
            ServiceResult serviceResult = new ServiceResult();
            serviceFactory.getSubscriptionService().subscribe(userSubscriptionList, serviceResult);

            if(serviceResult.isDone()) {
                Integer paymentId = (Integer) serviceResult.getResultObject();

                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute(SessionAttribute.CURRENT_CART_ITEM.getName());
                httpSession.removeAttribute(SessionAttribute.CURRENT_SIZE_CART_ITEM.getName());

                response.sendRedirect(String.format(PAYMENT_PAGE, paymentId));
            } else {
                response.sendRedirect(CART_ERROR);
            }

        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendRedirect(CART_ERROR);
        }
    }

    private List<UserSubscription> handleRequest(HttpServletRequest request) {
        List<UserSubscription> userSubscriptions = new ArrayList<>();

        HttpSession httpSession = request.getSession();

        int userId = (int) httpSession.getAttribute(SessionAttribute.CURRENT_USER_ID.getName());

        String[] subscriptionVariantsIds =  request.getParameterValues("subscriptionVariants");
        String[] subscriptionTypesIds =  request.getParameterValues("subscriptionTypes");

        int addressId = Integer.parseInt(request.getParameter(UserAddressCharacteristic.ID.getName()));

        Payment payment = new Payment();
        payment.setAmount(Double.parseDouble(request.getParameter("totalCost")));

        for (int i = 0; i < subscriptionVariantsIds.length; i++) {
            UserSubscription userSubscription = new UserSubscription();

            int idSubscriptionVariant = Integer.parseInt(subscriptionVariantsIds[i]);
            int idSubscriptionType = Integer.parseInt(subscriptionTypesIds[i]);

            UserAddress userAddress = new UserAddress();
            userAddress.setIdUser(userId);
            userAddress.setIdAddress(addressId);
            userSubscription.setUserAddress(userAddress);

            SubscriptionType subscriptionType = new SubscriptionType();
            subscriptionType.setId(idSubscriptionType);

            SubscriptionVariant subscriptionVariant = new SubscriptionVariant();
            subscriptionVariant.setId(idSubscriptionVariant);
            subscriptionVariant.setSubscriptionType(subscriptionType);
            userSubscription.setSubscriptionVariant(subscriptionVariant);

            userSubscription.setPayment(payment);

            userSubscriptions.add(userSubscription);
        }

        return userSubscriptions;
    }
}
