package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.UserLocale;
import by.training.zorich.controller.GetRequestParameterType;
import by.training.zorich.controller.SessionAttribute;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.JspRepository;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(ChangeLocaleHandler.class);
    private ServiceFactory serviceFactory;
    private JspRepository jspRepository;

    public ChangeLocaleHandler(ServiceFactory serviceFactory, JspRepository jspRepository) {
        this.serviceFactory = serviceFactory;
        this.jspRepository = jspRepository;
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLocale = request.getParameter(GetRequestParameterType.LOCALE.getName());

        HttpSession httpSession = request.getSession();
        Integer currentUserId = (Integer) httpSession.getAttribute(SessionAttribute.CURRENT_USER_ID.getName());

        ServiceResult serviceResult = new ServiceResult();

        if(currentUserId != null) {
            try {
                serviceFactory.getUserService().changeLocale(currentUserId, UserLocale.getUserLocaleByName(newLocale), serviceResult);
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        httpSession.setAttribute(SessionAttribute.CURRENT_LOCALE.getName(), UserLocale.getUserLocaleByName(newLocale));

        jspRepository.readdressToJsp(request, response);
    }
}
