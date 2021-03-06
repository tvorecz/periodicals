/**
 * Handler for creating cart-page.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.SubscriptionVariant;
import by.training.zorich.bean.UserAddress;
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

public class SubscriberCartPageHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SubscriberCartPageHandler.class);
    private final static String SUBSCRIPTION_VARIANTS = "subscriptionVariants";
    private final static String TOTAL_COST = "totalCost";
    private final static String USER_ADDRESSES = "userAddresses";

    private final ServiceFactory serviceFactory;

    public SubscriberCartPageHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        HttpSession httpSession = request.getSession();

        List<Integer> subscriptionVariantIds =
                (List<Integer>) httpSession.getAttribute(SessionAttribute.CURRENT_CART_ITEM.getName());
        Integer userId = (Integer) httpSession.getAttribute(SessionAttribute.CURRENT_USER_ID.getName());


        List<SubscriptionVariant> subscriptionVariants = null;
        List<UserAddress> userAddresses = null;

        try {
            ServiceResult serviceResult = new ServiceResult();

            if (subscriptionVariantIds != null) {
                serviceFactory.getSubscriptionService().getSubscriptionVariantsByIds(subscriptionVariantIds,
                                                                                     serviceResult);


                if (serviceResult.isDone()) {
                    subscriptionVariants = (List<SubscriptionVariant>) serviceResult.getResultObject();

                    request.setAttribute(SUBSCRIPTION_VARIANTS, subscriptionVariants);

                    double totalCost = 0;

                    for (SubscriptionVariant subscriptionVariant : subscriptionVariants) {
                        totalCost += subscriptionVariant.getActualCost();
                    }

                    request.setAttribute(TOTAL_COST, totalCost);
                }
            }

            serviceResult.clear();

            if (userId != null) {
                serviceFactory.getUserService().getAllUserAddresses(userId, serviceResult);
            }

            if (userId != null && serviceResult.isDone()) {
                userAddresses = (List<UserAddress>) serviceResult.getResultObject();
            }

            request.setAttribute(USER_ADDRESSES, userAddresses);
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendRedirect(JspPagePath.ERROR);
        }
    }
}
