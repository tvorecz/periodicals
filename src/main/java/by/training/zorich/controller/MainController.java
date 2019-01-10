package by.training.zorich.controller;

import by.training.zorich.controller.builder_layer.impl.CommandHandlerBuilderImpl;
import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.command_handler.JspRepository;
import by.training.zorich.controller.command_handler.exception.CommandException;
import by.training.zorich.controller.command_handler.impl.JspRepositoryImpl;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class MainController extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger(MainController.class);
    private static final long serialVersionUID = 4381375727757464524L;

    private CommandRepository commandRepository;
    private JspRepository jspRepository;
    private String pathToImages;

    @Override
    public void init() throws ServletException {
        try {
            commandRepository = new CommandHandlerBuilderImpl().build();
            pathToImages = getServletContext().getRealPath("/images");
        } catch (CommandException e) {
            LOGGER.error(e);
            throw new ServletException("Error during init servlet.",e);
        }

        jspRepository = JspRepositoryImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandHandler handler;

        Enumeration<String> parameterNames = req.getParameterNames();

        req.setAttribute("pathToImages", pathToImages);

        if(parameterNames.hasMoreElements()) {
            String commandName = req.getParameter(HandlerType.COMMAND.getName());
            if(commandName != null) {
                handler = commandRepository.getCommandHandler(HandlerType.getActionParameterByName(commandName));
                try {
                    handler.handle(req, resp);
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            } else {
                jspRepository.readdressToJsp(req, resp);
            }
        } else {
            jspRepository.readdressToJsp(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = null;

        req.setAttribute("pathToImages", pathToImages);

        if(ServletFileUpload.isMultipartContent(req)) {
            req.setCharacterEncoding("UTF-8");
            commandName = req.getRequestURI();
        } else {
            commandName = req.getParameter(HandlerType.COMMAND.getName());
        }

        CommandHandler handler = commandRepository.getCommandHandler(HandlerType.getActionParameterByName(commandName));

        try {
            handler.handle(req, resp);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}
