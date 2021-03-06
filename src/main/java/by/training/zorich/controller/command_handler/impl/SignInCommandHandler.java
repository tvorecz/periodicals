/**
 * Handler for login user.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.controller.CookieName;
import by.training.zorich.controller.HandlerType;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SignInCommandHandler.class);
    private final static String MAIN_PAGE = "/";
    private final static String FAILURE_LOGIN = "/login?message=failureLogin";
    private final ServiceFactory serviceFactory;

    public SignInCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        User user = handleRequest(request);

        ServiceResult serviceResult = new ServiceResult();

        try {
            serviceFactory.getUserService().authenticate(user, serviceResult);
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendRedirect(FAILURE_LOGIN);
        }

        if (serviceResult.isDone()) {
            setSessionParameters(request, serviceResult);

            setCookie(response, serviceResult);

            String returnPath = request.getParameter(HandlerType.RETURN_PAGE.getName());
            if (returnPath == null) {
                response.sendRedirect(MAIN_PAGE);
            } else {
                response.sendRedirect(returnPath);
            }
        } else {
            response.sendRedirect(FAILURE_LOGIN);
        }
    }

    private User handleRequest(HttpServletRequest request) {
        User user = new User();

        user.setLogin(request.getParameter(UserCharacteristic.LOGIN.getName()));
        user.setRealPassword(request.getParameter(UserCharacteristic.REAL_PASSWORD.getName()));

        return user;
    }

    private void setSessionParameters(HttpServletRequest request, ServiceResult serviceResult) {
        HttpSession session = request.getSession();

        User loginatedUser = (User) serviceResult.getResultObject();

        session.setAttribute(SessionAttribute.CURRENT_USER_ID.getName(), loginatedUser.getId());
        session.setAttribute(SessionAttribute.CURRENT_USER_NAME.getName(), loginatedUser.getLogin());
        session.setAttribute(SessionAttribute.CURRENT_USER_ROLE.getName(), loginatedUser.getRole());
        session.setAttribute(SessionAttribute.CURRENT_LOCALE.getName(), loginatedUser.getCurrentLocale());
    }

    private void setCookie(HttpServletResponse response, ServiceResult serviceResult) {
        UserLocale userLocale = ((User) serviceResult.getResultObject()).getCurrentLocale();

        Cookie cookie = new Cookie(CookieName.CURRENT_LOCALE.getName(), userLocale.getName());

        response.addCookie(cookie);
    }
}
