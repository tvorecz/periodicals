package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.User;
import by.training.zorich.controller.CommandType;
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

public class SignInCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SignInCommandHandler.class);
    private final static String MAIN_PAGE = "/";
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
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }

        if(serviceResult.isDone()) {
            setSessionParameters(request, serviceResult);

            String returnPath = request.getParameter(CommandType.RETURN_PAGE.getName());
            if(returnPath == null) {
//                request.getRequestDispatcher(MAIN_PAGE).forward(request, response);
                response.sendRedirect(MAIN_PAGE);
            } else {
//                request.getRequestDispatcher(returnPath).forward(request, response);
                response.sendRedirect(returnPath);
            }
        } else {
//            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
            response.sendRedirect(JspPagePath.ERROR);
        }

        return;
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
}
