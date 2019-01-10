package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.bean.*;
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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PeriodicalSearchCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(PeriodicalSearchCommandHandler.class);
    private final static String SEARCH_LINK = "/periodical/search?";
    private ServiceFactory serviceFactory;

    public PeriodicalSearchCommandHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                                 ServletException,
                                                                                 IOException,
                                                                                 CommandException {
        String keySearch = null;
        Integer periodicalTypeId = null;
        Integer periodicalThemeId = null;
        Integer subscriptionTypeId = null;
        Integer amountOnPage = null;
        Integer currentPage = null;

        if(request.getParameter("keySearch") != null && !request.getParameter("keySearch").equals("")) {
            keySearch = request.getParameter("keySearch");
        }

        if(request.getParameter("periodicalTypeId") != null && !request.getParameter("periodicalTypeId").equals("")) {
            periodicalTypeId = Integer.parseInt(request.getParameter("periodicalTypeId"));

        }

        if(request.getParameter("periodicalThemeId") != null && !request.getParameter("periodicalThemeId").equals("")) {
            periodicalThemeId = Integer.parseInt(request.getParameter("periodicalThemeId"));
        }

        if(request.getParameter("subscriptionTypeId") != null && !request.getParameter("subscriptionTypeId").equals("")) {
            subscriptionTypeId = Integer.parseInt(request.getParameter("subscriptionTypeId"));
        }

        if(request.getParameter("amountOnPage") != null && !request.getParameter("amountOnPage").equals("")) {
            amountOnPage = Integer.parseInt(request.getParameter("amountOnPage"));
        }

        if(request.getParameter("page") != null && !request.getParameter("page").equals("")) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        if(amountOnPage == null) {
            amountOnPage = 9;
        } else if(amountOnPage != 6 && amountOnPage != 9 && amountOnPage != 12 && amountOnPage != 15) {
            amountOnPage = 9;
        }

        if(currentPage == null) {
            currentPage = 1;
        }

        ServiceResult serviceResult = new ServiceResult();

        try {
            serviceFactory.getPeriodicalService().search(keySearch, periodicalTypeId, periodicalThemeId, subscriptionTypeId, amountOnPage, currentPage, serviceResult);

            if(serviceResult.isDone()) {
                List<Periodical> periodicals = (List<Periodical>) serviceResult.getResultObject();

                serviceResult.clear();

                serviceFactory.getPeriodicalService().getCountOfFoundPeriodicals(serviceResult);

                if(serviceResult.isDone()) {
                    Integer countOfFoundPeriodicals = (Integer) serviceResult.getResultObject();

                    serviceResult.clear();

                    serviceFactory.getPeriodicalService().getAllPeriodicalTypes(serviceResult);

                    if(serviceResult.isDone()) {
                        List<PeriodicalType> periodicalTypes = (List<PeriodicalType>) serviceResult.getResultObject();

                        serviceResult.clear();

                        serviceFactory.getPeriodicalService().getAllPeriodicalTypes(serviceResult);

                        if(serviceResult.isDone()) {
                            List<PeriodicalTheme> periodicalThemes = (List<PeriodicalTheme>) serviceResult.getResultObject();

                            serviceResult.clear();

                            serviceFactory.getSubscriptionService().getAllSubscriptionTypes(serviceResult);

                            if(serviceResult.isDone()) {
                                List<SubscriptionType> subscriptionTypes = (List<SubscriptionType>) serviceResult.getResultObject();

                                int countPage = 0;

                                if(countOfFoundPeriodicals % amountOnPage == 0) {
                                    countPage = countOfFoundPeriodicals / amountOnPage;
                                } else {
                                    countPage = countOfFoundPeriodicals / amountOnPage + 1;
                                }

                                Integer[] pageNumbers = createPageNumbersArray(countPage, currentPage);

                                request.setAttribute("keySearch", keySearch);
                                request.setAttribute("periodicals", periodicals);
                                request.setAttribute("countPage", countPage);
                                request.setAttribute("currentPage", currentPage);
                                request.setAttribute("currentQueryLink", createCurrentSearchLink(keySearch, periodicalTypeId, periodicalThemeId, subscriptionTypeId, amountOnPage, currentPage));
                                request.setAttribute("selectedPeriodicalTypeId", periodicalTypeId);
                                request.setAttribute("selectedPeriodicalThemeId", periodicalThemeId);
                                request.setAttribute("selectedSubscriptionTypeId", subscriptionTypeId);
                                request.setAttribute("selectedAmountOnPage", amountOnPage);
                                request.setAttribute("periodicalTypes", periodicalTypes);
                                request.setAttribute("periodicalThemes", periodicalThemes);
                                request.setAttribute("subscriptionTypes", subscriptionTypes);
                                request.setAttribute("pageNumbers", pageNumbers);
                            }
                        }
                    }
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }

    private String createCurrentSearchLink(String keySearch,
                                           Integer periodicalTypeId,
                                           Integer periodicalThemeId,
                                           Integer subscriptionTypeId,
                                           Integer amountOnPage,
                                           Integer page){
        StringBuffer stringBuffer = new StringBuffer(SEARCH_LINK);

        if(keySearch != null) {
            stringBuffer.append("keySearch=" + keySearch);
        }

        if(periodicalTypeId != null) {
            stringBuffer.append("periodicalTypeId=" + periodicalTypeId + "&");
        }

        if(periodicalThemeId != null) {
            stringBuffer.append("periodicalThemeId=" + periodicalThemeId + "&");
        }

        if(subscriptionTypeId != null) {
            stringBuffer.append("subscriptionTypeId=" + subscriptionTypeId + "&");
        }

        if(amountOnPage != null) {
            stringBuffer.append("amountOnPage" + amountOnPage + "&");
        }

        String resultString = stringBuffer.toString();

        char endChar = resultString.charAt(resultString.length() - 1);

        if(endChar == '&') {
            resultString = resultString.substring(0, resultString.length() - 1);
        }

        return resultString;
    }

    private Integer[] createPageNumbersArray(int amountPage, int currentPage) {
        ArrayDeque<Integer> integerQueue = new ArrayDeque<>();

        integerQueue.add(currentPage);

        if(currentPage < (amountPage - 1)) {
            integerQueue.addLast(currentPage + 1);
            integerQueue.addLast(currentPage + 2);
        } else if(currentPage < (amountPage)) {
            integerQueue.addLast(currentPage + 1);
        }

        if(currentPage > 2) {
            integerQueue.addFirst(currentPage - 1);
            integerQueue.addFirst(currentPage - 2);
        } else if(currentPage > 1) {
            integerQueue.addFirst(currentPage - 1);
        }

        return integerQueue.toArray(new Integer[0]);
    }
}
