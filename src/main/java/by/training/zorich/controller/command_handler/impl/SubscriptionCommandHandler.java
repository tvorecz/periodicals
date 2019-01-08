package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.*;
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

public class SubscriptionCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SubscriptionCommandHandler.class);
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
            }

        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }


    }

    private List<UserSubscription> handleRequest(HttpServletRequest request) {
        List<UserSubscription> userSubscriptions = null;

        HttpSession httpSession = request.getSession();

        int userId = (int) httpSession.getAttribute(SessionAttribute.CURRENT_USER_ID.getName());

        String[] subscriptionVariantsIds =  request.getParameterValues("subscriptionVariants");

        int addressId = Integer.parseInt(request.getParameter(UserAddressCharacteristic.ID.getName()));

        for (String subscriptionVariantId : subscriptionVariantsIds) {
            UserSubscription userSubscription = new UserSubscription();

            int idSubscriptionVariant = Integer.parseInt(subscriptionVariantId);

            UserAddress userAddress = new UserAddress();
            userAddress.setIdUser(userId);
            userAddress.setIdAdress(addressId);
            userSubscription.setUserAddress(userAddress);

            SubscriptionVariant subscriptionVariant = new SubscriptionVariant();
            subscriptionVariant.setId(idSubscriptionVariant);
            userSubscription.setSubscriptionVariant(subscriptionVariant);

            userSubscriptions.add(userSubscription);

        }

        return userSubscriptions;
    }
}
