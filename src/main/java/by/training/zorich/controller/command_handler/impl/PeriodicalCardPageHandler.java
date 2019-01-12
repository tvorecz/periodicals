package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.Periodical;
import by.training.zorich.bean.ServiceResult;
import by.training.zorich.bean.SubscriptionVariant;
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

public class PeriodicalCardPageHandler implements CommandHandler {
    private final static String PERIODICAL = "/periodical/";
    private final static String ERROR_404 = "/error404";
    private final static Logger LOGGER = LogManager.getLogger(PeriodicalCardPageHandler.class);
    private ServiceFactory serviceFactory;

    public PeriodicalCardPageHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {

        String periodicalId = request.getRequestURI().replace(PERIODICAL, "");

        if(periodicalId == null) {
            response.sendRedirect(JspPagePath.ERROR);
        } else {
            Integer idPeriodical = Integer.parseInt(periodicalId);
            ServiceResult serviceResult = new ServiceResult();
            try {
                serviceFactory.getPeriodicalService().getPeriodicalById(idPeriodical, serviceResult);

                if(serviceResult.isDone()) {
                    Periodical periodical = (Periodical) serviceResult.getResultObject();
                    serviceResult.clear();

                    serviceFactory.getSubscriptionService().getAllSubscriptionVariantsForPeriodical(periodical, serviceResult);

                    if (serviceResult.isDone()) {
                        List<SubscriptionVariant> subscriptionVariants = (List<SubscriptionVariant>) serviceResult.getResultObject();

                        request.setAttribute("subscriptionVariants", subscriptionVariants);
                        request.setAttribute("periodical", periodical);
                    } else {
                        response.sendRedirect(ERROR_404);
                    }

                } else {
                    response.sendRedirect(ERROR_404);
                }

            } catch (ServiceException e) {
                LOGGER.error(e);
                response.sendRedirect(ERROR_404);
            }
        }
    }
}
