/**
 * Handler for periodical search.
 *
 * @autor Dzmitry Zorich
 * @version 1.1
 */

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
import java.util.List;

public class PeriodicalSearchCommandHandler implements CommandHandler {
    private final static Logger LOGGER = LogManager.getLogger(PeriodicalSearchCommandHandler.class);
    private final static String EMPTY = "";
    private final static String EQUAL_SIGN = "=";
    private final static String AND = "&";
    private final static String SEARCH_LINK = "/periodical/search?";
    private final static String KEY_SEARCH = "keySearch";
    private final static String PERIODICALS = "periodicals";
    private final static String COUNT_PAGE = "countPage";
    private final static String CURRENT_PAGE = "currentPage";
    private final static String CURRENT_QUERY_LINK = "currentQueryLink";
    private final static String SELECTED_PERIODICAL_TYPE_ID = "selectedPeriodicalTypeId";
    private final static String SELECTED_PERIODICAL_THEME_ID = "selectedPeriodicalThemeId";
    private final static String SELECTED_SUBSCRIPTION_TYPE_ID = "selectedSubscriptionTypeId";
    private final static String SELECTED_AMOUNT_ON_PAGE = "selectedAmountOnPage";
    private final static String PERIODICALS_TYPES = "periodicalTypes";
    private final static String PERIODICAL_THEMES = "periodicalThemes";
    private final static String SUBSCRIPTION_TYPES = "subscriptionTypes";
    private final static String PAGE_NUMBERS = "pageNumbers";
    private final static String MESSAGE = "message";
    private final static String MESSAGE_NOTHING = "nothingFound";

    private final static String PARAMETER_PERIODICAL_TYPE_ID = "periodicalTypeId";
    private final static String PARAMETER_PERIODICAL_THEME_ID = "periodicalThemeId";
    private final static String PARAMETER_SUBSCRIPTION_TYPE_ID = "subscriptionTypeId";
    private final static String AMOUNT_ON_PAGE = "amountOnPage";
    private final static String PAGE = "page";

    private final ServiceFactory serviceFactory;

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

        if (request.getParameter(KEY_SEARCH) != null && !request.getParameter(KEY_SEARCH).equals(EMPTY)) {
            keySearch = request.getParameter(KEY_SEARCH);
        }

        if (request.getParameter(PARAMETER_PERIODICAL_TYPE_ID) != null && !request.getParameter(
                PARAMETER_PERIODICAL_TYPE_ID).equals(
                EMPTY)) {
            periodicalTypeId = Integer.parseInt(request.getParameter(PARAMETER_PERIODICAL_TYPE_ID));

        }

        if (request.getParameter(PARAMETER_PERIODICAL_THEME_ID) != null && !request.getParameter(
                PARAMETER_PERIODICAL_THEME_ID).equals(
                EMPTY)) {
            periodicalThemeId = Integer.parseInt(request.getParameter(PARAMETER_PERIODICAL_THEME_ID));
        }

        if (request.getParameter(PARAMETER_SUBSCRIPTION_TYPE_ID) != null && !request.getParameter(
                PARAMETER_SUBSCRIPTION_TYPE_ID).equals(
                EMPTY)) {
            subscriptionTypeId = Integer.parseInt(request.getParameter(PARAMETER_SUBSCRIPTION_TYPE_ID));
        }

        if (request.getParameter(AMOUNT_ON_PAGE) != null && !request.getParameter(AMOUNT_ON_PAGE).equals(EMPTY)) {
            amountOnPage = Integer.parseInt(request.getParameter(AMOUNT_ON_PAGE));
        }

        if (request.getParameter(PAGE) != null && !request.getParameter(PAGE).equals(EMPTY)) {
            currentPage = Integer.parseInt(request.getParameter(PAGE));
        }

        if (amountOnPage == null) {
            amountOnPage = 9;
        } else if (amountOnPage != 6 && amountOnPage != 9 && amountOnPage != 12 && amountOnPage != 15) {
            amountOnPage = 9;
        }

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }

        ServiceResult serviceResult = new ServiceResult();

        try {
            serviceFactory.getPeriodicalService().search(keySearch,
                                                         periodicalTypeId,
                                                         periodicalThemeId,
                                                         subscriptionTypeId,
                                                         amountOnPage,
                                                         currentPage,
                                                         serviceResult);

            if (serviceResult.isDone()) {
                List<Periodical> periodicals = (List<Periodical>) serviceResult.getResultObject();

                serviceResult.clear();

                serviceFactory.getPeriodicalService().getCountOfFoundPeriodicals(serviceResult);

                if (serviceResult.isDone()) {
                    Integer countOfFoundPeriodicals = (Integer) serviceResult.getResultObject();

                    serviceResult.clear();

                    serviceFactory.getPeriodicalService().getAllPeriodicalTypes(serviceResult);

                    if (serviceResult.isDone()) {
                        List<PeriodicalType> periodicalTypes = (List<PeriodicalType>) serviceResult.getResultObject();

                        serviceResult.clear();

                        serviceFactory.getPeriodicalService().getAllPeriodicalThemes(serviceResult);

                        if (serviceResult.isDone()) {
                            List<PeriodicalTheme> periodicalThemes =
                                    (List<PeriodicalTheme>) serviceResult.getResultObject();

                            serviceResult.clear();

                            serviceFactory.getSubscriptionService().getAllSubscriptionTypes(serviceResult);

                            if (serviceResult.isDone()) {
                                List<SubscriptionType> subscriptionTypes =
                                        (List<SubscriptionType>) serviceResult.getResultObject();

                                int countPage = 0;

                                if (countOfFoundPeriodicals % amountOnPage == 0) {
                                    countPage = countOfFoundPeriodicals / amountOnPage;
                                } else {
                                    countPage = countOfFoundPeriodicals / amountOnPage + 1;
                                }

                                if (currentPage > countPage) {
                                    if (countPage != 0) {
                                        response.sendRedirect(createCurrentSearchLink(keySearch,
                                                                                      periodicalTypeId,
                                                                                      periodicalThemeId,
                                                                                      subscriptionTypeId,
                                                                                      amountOnPage,
                                                                                      countPage));
                                        return;
                                    }
                                    if (countPage == 0) {
                                        currentPage = 1;
                                    }
                                } else if (currentPage < 1) {
                                    response.sendRedirect(createCurrentSearchLink(keySearch,
                                                                                  periodicalTypeId,
                                                                                  periodicalThemeId,
                                                                                  subscriptionTypeId,
                                                                                  amountOnPage,
                                                                                  1));
                                    return;
                                }

                                Integer[] pageNumbers = createPageNumbersArray(countPage, currentPage);

                                request.setAttribute(KEY_SEARCH, keySearch);
                                request.setAttribute(PERIODICALS, periodicals);
                                request.setAttribute(COUNT_PAGE, countPage);
                                request.setAttribute(CURRENT_PAGE, currentPage);
                                request.setAttribute(CURRENT_QUERY_LINK,
                                                     createCurrentSearchLink(keySearch,
                                                                             periodicalTypeId,
                                                                             periodicalThemeId,
                                                                             subscriptionTypeId,
                                                                             amountOnPage));
                                request.setAttribute(SELECTED_PERIODICAL_TYPE_ID, periodicalTypeId);
                                request.setAttribute(SELECTED_PERIODICAL_THEME_ID, periodicalThemeId);
                                request.setAttribute(SELECTED_SUBSCRIPTION_TYPE_ID, subscriptionTypeId);
                                request.setAttribute(SELECTED_AMOUNT_ON_PAGE, amountOnPage);
                                request.setAttribute(PERIODICALS_TYPES, periodicalTypes);
                                request.setAttribute(PERIODICAL_THEMES, periodicalThemes);
                                request.setAttribute(SUBSCRIPTION_TYPES, subscriptionTypes);
                                request.setAttribute(PAGE_NUMBERS, pageNumbers);

                                if (countPage == 0) {
                                    request.setAttribute(MESSAGE, MESSAGE_NOTHING);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendRedirect(SEARCH_LINK);
        }


    }

    private String createCurrentSearchLink(String keySearch,
                                           Integer periodicalTypeId,
                                           Integer periodicalThemeId,
                                           Integer subscriptionTypeId,
                                           Integer amountOnPage,
                                           Integer page) {
        StringBuffer stringBuffer = new StringBuffer();

        if (keySearch != null || periodicalThemeId != null || periodicalTypeId != null || subscriptionTypeId != null || amountOnPage != null) {
            stringBuffer.append(createCurrentSearchLink(keySearch,
                                                        periodicalTypeId,
                                                        periodicalThemeId,
                                                        subscriptionTypeId,
                                                        amountOnPage));
            stringBuffer.append(AND);
        }


        if (page != null) {
            stringBuffer.append(PAGE + EQUAL_SIGN + page);
        }

        String resultString = stringBuffer.toString();

        char endChar = resultString.charAt(resultString.length() - 1);

        if (endChar == '&') {
            resultString = resultString.substring(0, resultString.length() - 1);
        }

        return resultString;
    }

    private String createCurrentSearchLink(String keySearch,
                                           Integer periodicalTypeId,
                                           Integer periodicalThemeId,
                                           Integer subscriptionTypeId,
                                           Integer amountOnPage) {
        StringBuffer stringBuffer = new StringBuffer(SEARCH_LINK);

        if (keySearch != null) {
            stringBuffer.append(KEY_SEARCH + EQUAL_SIGN + keySearch + AND);
        }

        if (periodicalTypeId != null) {
            stringBuffer.append(PARAMETER_PERIODICAL_TYPE_ID + EQUAL_SIGN + periodicalTypeId + AND);
        }

        if (periodicalThemeId != null) {
            stringBuffer.append(PARAMETER_PERIODICAL_THEME_ID + EQUAL_SIGN + periodicalThemeId + AND);
        }

        if (subscriptionTypeId != null) {
            stringBuffer.append(PARAMETER_SUBSCRIPTION_TYPE_ID + EQUAL_SIGN + subscriptionTypeId + AND);
        }

        if (amountOnPage != null) {
            stringBuffer.append(AMOUNT_ON_PAGE + EQUAL_SIGN + amountOnPage + AND);
        }

        String resultString = stringBuffer.toString();

        char endChar = resultString.charAt(resultString.length() - 1);

        if (endChar == '&') {
            resultString = resultString.substring(0, resultString.length() - 1);
        }

        return resultString;
    }

    private Integer[] createPageNumbersArray(int amountPage, int currentPage) {
        ArrayDeque<Integer> integerQueue = new ArrayDeque<>();

        integerQueue.add(currentPage);

        if (currentPage < (amountPage - 1)) {
            integerQueue.addLast(currentPage + 1);
            integerQueue.addLast(currentPage + 2);
        } else if (currentPage < (amountPage)) {
            integerQueue.addLast(currentPage + 1);
        }

        if (currentPage > 2) {
            integerQueue.addFirst(currentPage - 1);
            integerQueue.addFirst(currentPage - 2);
        } else if (currentPage > 1) {
            integerQueue.addFirst(currentPage - 1);
        }

        return integerQueue.toArray(new Integer[0]);
    }
}
