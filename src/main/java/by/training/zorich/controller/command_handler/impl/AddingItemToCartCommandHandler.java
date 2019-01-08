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
import java.util.ArrayList;
import java.util.List;

public class AddingItemToCartCommandHandler implements CommandHandler {
    private final static String RETURN_PATH_PARAMETER = "returnPath";
    private final static String ITEM_ID_PARAMETER = "type";

    private ServiceFactory serviceFactory;

    public AddingItemToCartCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        HttpSession httpSession = request.getSession();

        List<Integer> cartItems = (List<Integer>) httpSession.getAttribute(SessionAttribute.CURRENT_CART_ITEM.getName());

        if(cartItems == null) {
            cartItems = new ArrayList<>();
        }

        String idForParse = request.getParameter(ITEM_ID_PARAMETER);

        Integer idItem = Integer.parseInt(idForParse);

        cartItems.add(idItem);

        httpSession.setAttribute(SessionAttribute.CURRENT_CART_ITEM.getName(), cartItems);
        httpSession.setAttribute(SessionAttribute.CURRENT_SIZE_CART_ITEM.getName(), cartItems.size());

        String returnPath = request.getParameter(RETURN_PATH_PARAMETER);

        response.sendRedirect(returnPath);
    }
}
