package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
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

public class AdditionUserAddressCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SignUpCommandHandler.class);
    private final static String CART_PAGE_SUCCESS = "/subscriber/cart?message=addAddressSuccess";
    private final static String CART_PAGE_ERROR = "/subscriber/cart?message=addAddressError";
    private ServiceFactory serviceFactory;

    public AdditionUserAddressCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        HttpSession httpSession = request.getSession();

        Integer userId = (Integer) httpSession.getAttribute(SessionAttribute.CURRENT_USER_ID.getName());

        if(userId == null) {
            response.sendRedirect(CART_PAGE_ERROR);
        } else {
            UserAddress userAddress = new UserAddress();

            String address = request.getParameter("newAddress");

            if(address == null) {
                response.sendRedirect(CART_PAGE_ERROR);
            } else {
                userAddress.setAddress(address);
                userAddress.setIdUser(userId);

                ServiceResult serviceResult = new ServiceResult();

                try {
                    serviceFactory.getUserService().addAddress(userAddress, serviceResult);

                    if(serviceResult.isDone()) {
                        response.sendRedirect(CART_PAGE_SUCCESS);
                    } else {
                        response.sendRedirect(CART_PAGE_ERROR);
                    }
                } catch (ServiceException e) {
                    LOGGER.error(e);
                    request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
                }


            }


        }
    }
}
