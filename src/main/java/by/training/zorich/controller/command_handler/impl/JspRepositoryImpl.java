package by.training.zorich.controller.command_handler.impl;

import by.training.zorich.controller.HandlerType;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.command_handler.JspRepository;
import by.training.zorich.controller.command_handler.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JspRepositoryImpl implements JspRepository {
    private final static String PERIODICAL = "/periodical/";
    private final static String PAYMENT = "/subscriber/payment/";
    private Map<String, String> jspMap;
    private CommandRepository commandRepository;

    private JspRepositoryImpl() {
        jspMap = new HashMap<>();
    }

    private static class JspRepositoryImplHelper{
        private static final JspRepositoryImpl COMMAND_REPOSITORY = new JspRepositoryImpl();
    }

    public static JspRepositoryImpl getInstance() {
        return JspRepositoryImplHelper.COMMAND_REPOSITORY;
    }

    @Override
    public void init(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;

        jspMap.put("/login","/WEB-INF/jsp/logination/login.jsp");
        jspMap.put("/register","/WEB-INF/jsp/logination/register.jsp");
        jspMap.put("/main","/WEB-INF/jsp/index/index.jsp");
        jspMap.put("/index","/WEB-INF/jsp/index/index.jsp");
        jspMap.put("/","/WEB-INF/jsp/index/index.jsp");
        jspMap.put("/admin/add", "/WEB-INF/jsp/admin/add.jsp");
        jspMap.put("/periodical/", "/WEB-INF/jsp/periodical/card.jsp");
        jspMap.put("/subscriber/cart", "/WEB-INF/jsp/subscriber/cart.jsp");
        jspMap.put("/subscriber/payment/", "/WEB-INF/jsp/subscriber/payment.jsp");

    }

    @Override
    public void readdressToJsp(HttpServletRequest request, HttpServletResponse response) throws
                                                                                         ServletException,
                                                                                         IOException {
        String requestURI = request.getRequestURI();

        request.setAttribute("path", requestURI);

        prepareRequestForPage(request, response, requestURI);

        if(requestURI.contains(PERIODICAL)) {
            requestURI = PERIODICAL;
        } else if(requestURI.contains(PAYMENT)) {
            requestURI = PAYMENT;
        }

        request.getRequestDispatcher(jspMap.get(requestURI)).forward(request, response);
    }

    private void prepareRequestForPage(HttpServletRequest request, HttpServletResponse response, String uri) throws
                                                                                                          ServletException,
                                                                                                          IOException {
        HandlerType handlerType = null;

        if(uri.contains(PERIODICAL)) {
            handlerType = HandlerType.getActionParameterByName(PERIODICAL);
        } else if (uri.contains(PAYMENT)){
            handlerType = HandlerType.getActionParameterByName(PAYMENT);
        } else {
            handlerType = HandlerType.getActionParameterByName(uri);
        }

        if(handlerType != null) {
            CommandHandler handler = commandRepository.getCommandHandler(handlerType);
            try {
                handler.handle(request, response);
            } catch (CommandException e) {
                e.printStackTrace();
            }
        }
    }
}
