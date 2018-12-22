package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.controller.SessionAttribute;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.bean.UserCharacteristic;
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
    private ServiceFactory serviceFactory;

    public SignUpCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = handleRequest(request);

        ServiceResult serviceResult = new ServiceResult();

        try {
            serviceFactory.getUserService().register(user, serviceResult);
            response.sendRedirect(LOGIN_PAGE);
//            request.getRequestDispatcher(JspPagePath.REG_OK).forward(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }
    }

    private User handleRequest(HttpServletRequest request) {
        User user = new User();

        user.setLogin(request.getParameter(UserCharacteristic.LOGIN.getName()));
        user.setRealPassword(request.getParameter(UserCharacteristic.REAL_PASSWORD.getName()));
        user.setEmail(request.getParameter(UserCharacteristic.EMAIL.getName()));

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
