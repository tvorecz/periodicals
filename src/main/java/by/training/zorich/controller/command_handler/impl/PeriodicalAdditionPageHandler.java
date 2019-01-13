/**
 * Handler for creating adding periodical page.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.PeriodicalTheme;
import by.training.zorich.bean.PeriodicalType;
import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.SubscriptionType;
import by.training.zorich.controller.JspPagePath;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.service.exception.ServiceException;
import by.training.zorich.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class PeriodicalAdditionPageHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(PeriodicalAdditionPageHandler.class);

    private final static String PERIODICAL_THEME_LIST = "periodicalThemeList";
    private final static String PERIODICAL_TYPE_LIST = "periodicalTypeList";
    private final static String SUBSCRIPTION_TYPE_LIST = "subscriptionTypeList";

    private final ServiceFactory serviceFactory;

    public PeriodicalAdditionPageHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        List<PeriodicalTheme> periodicalThemeList = null;
        List<PeriodicalType> periodicalTypeList = null;
        List<SubscriptionType> subscriptionTypeList = null;

        ServiceResult serviceResult = new ServiceResult();

        try {
            serviceFactory.getPeriodicalService().getAllPeriodicalThemes(serviceResult);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }

        periodicalThemeList = (List<PeriodicalTheme>) serviceResult.getResultObject();

        serviceResult.clear();

        try {
            serviceFactory.getPeriodicalService().getAllPeriodicalTypes(serviceResult);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }

        periodicalTypeList = (List<PeriodicalType>) serviceResult.getResultObject();

        serviceResult.clear();

        try {
            serviceFactory.getSubscriptionService().getAllSubscriptionTypes(serviceResult);
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.getRequestDispatcher(JspPagePath.ERROR).forward(request, response);
        }

        subscriptionTypeList = (List<SubscriptionType>) serviceResult.getResultObject();

        request.setAttribute(PERIODICAL_THEME_LIST, periodicalThemeList);
        request.setAttribute(PERIODICAL_TYPE_LIST, periodicalTypeList);
        request.setAttribute(SUBSCRIPTION_TYPE_LIST, subscriptionTypeList);
    }
}
