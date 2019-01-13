/**
 * Handler for register user.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.controller.SessionAttribute;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.bean.UserCharacteristic;
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

public class SignUpCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SignUpCommandHandler.class);
    private final static String LOGIN_PAGE = "/login?message=success";
    private final static String REGISTER_PAGE_WITH_ERROR = "/register?message=errorRegister";
    private final static String CONFIRM_PASSWORD_INPUT = "confirm";
    private final ServiceFactory serviceFactory;

    public SignUpCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        User user = handleRequest(request);

        if(user.getRealPassword() != null) {
            ServiceResult serviceResult = new ServiceResult();

            try {

                serviceFactory.getUserService().register(user, serviceResult);

                if(serviceResult.isDone()) {
                    response.sendRedirect(LOGIN_PAGE);
                } else {
                    response.sendRedirect(REGISTER_PAGE_WITH_ERROR);
                }
//            request.getRequestDispatcher(JspPagePath.REG_OK).forward(request, response);
            } catch (ServiceException e) {
                LOGGER.error(e);
                request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
            }
        } else {
            response.sendRedirect(REGISTER_PAGE_WITH_ERROR);
        }
    }

    private User handleRequest(HttpServletRequest request) {
        User user = new User();

        user.setLogin(request.getParameter(UserCharacteristic.LOGIN.getName()));
        user.setEmail(request.getParameter(UserCharacteristic.EMAIL.getName()));

        user.setRealPassword(request.getParameter(UserCharacteristic.REAL_PASSWORD.getName()));
        if(!user.getRealPassword().equals(request.getParameter(CONFIRM_PASSWORD_INPUT))) {
            user.setRealPassword(null);
        }

        HttpSession httpSession = request.getSession();
        UserLocale currentLocale = (UserLocale) httpSession.getAttribute(SessionAttribute.CURRENT_LOCALE.getName());

        if(currentLocale == null) {
            user.setCurrentLocale(UserLocale.RU);
        } else {
            user.setCurrentLocale(currentLocale);
        }

        return user;
    }
}
