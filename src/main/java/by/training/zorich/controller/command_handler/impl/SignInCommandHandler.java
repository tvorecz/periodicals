package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.const_parameter.JspPagePath;
import by.training.zorich.controller.const_parameter.UserCharacteristic;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SignInCommandHandler.class);
    private ServiceFactory serviceFactory;

    public SignInCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = handleRequest(request);

        ServiceResult serviceResult = new ServiceResult();

        try {
            serviceFactory.getUserService().authenticate(user, serviceResult);
        } catch (ServiceException e) {
            LOGGER.error(e.getStackTrace());
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }

        if(serviceResult.isDone()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", serviceResult.getResultObject());
            request.getRequestDispatcher(JspPagePath.SIGNIN_OK).forward(request, response);
        } else {
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }
    }

    private User handleRequest(HttpServletRequest request) {
        User user = new User();

        user.setLogin(request.getParameter(UserCharacteristic.LOGIN.getName()));
        user.setRealPassword(request.getParameter(UserCharacteristic.REAL_PASSWORD.getName()));

        return user;
    }
}
