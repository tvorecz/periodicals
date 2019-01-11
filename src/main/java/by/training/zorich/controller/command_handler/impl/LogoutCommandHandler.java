package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.controller.SessionAttribute;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(SignInCommandHandler.class);
    private final static String MAIN_PAGE = "/";
    private final static String TARGET_PATH_COMMAND = "target";
    private final static String ADMIN_TARGET_PATH = "admin";
    private final ServiceFactory serviceFactory;

    public LogoutCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute(SessionAttribute.CURRENT_USER_ID.getName());
        httpSession.removeAttribute(SessionAttribute.CURRENT_USER_NAME.getName());
        httpSession.removeAttribute(SessionAttribute.CURRENT_USER_ROLE.getName());

        String returnPath = request.getParameter(TARGET_PATH_COMMAND);

        if(returnPath.contains(ADMIN_TARGET_PATH)) {
            response.sendRedirect(MAIN_PAGE);
        } else {
            response.sendRedirect(returnPath);
        }
    }
}
