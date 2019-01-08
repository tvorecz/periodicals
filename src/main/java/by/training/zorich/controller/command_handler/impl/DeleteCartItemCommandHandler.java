package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.controller.SessionAttribute;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteCartItemCommandHandler implements CommandHandler {
    private final static String CART_PAGE = "/subscriber/cart";
    private ServiceFactory serviceFactory;

    public DeleteCartItemCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        HttpSession httpSession = request.getSession();

        List<Integer> subscriprionVariantsIds = (List<Integer>) httpSession.getAttribute(SessionAttribute.CURRENT_CART_ITEM.getName());

        Integer idForDelete = Integer.parseInt(request.getParameter("idSubscriptionVariant"));

        if(subscriprionVariantsIds != null) {
            int indexForRemove = 0;

            for (Integer subscriprionVariantsId : subscriprionVariantsIds) {
                if(idForDelete.equals(subscriprionVariantsId)) {
                    break;
                } else {
                    indexForRemove++;
                }
            }

            subscriprionVariantsIds.remove(indexForRemove);

            httpSession.setAttribute(SessionAttribute.CURRENT_CART_ITEM.getName(), subscriprionVariantsIds);

            if(subscriprionVariantsIds.size() > 0) {
                httpSession.setAttribute(SessionAttribute.CURRENT_SIZE_CART_ITEM.getName(), subscriprionVariantsIds.size());
            } else {
                httpSession.removeAttribute(SessionAttribute.CURRENT_SIZE_CART_ITEM.getName());
            }
        }

        response.sendRedirect(CART_PAGE);
    }
}
