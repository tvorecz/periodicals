package by.training.zorich.controller;

import by.training.zorich.controller.command_handler.CommandHandler;
import by.training.zorich.controller.command_handler.CommandRepository;
import by.training.zorich.controller.command_handler.impl.CommandRepositoryImpl;
import by.training.zorich.controller.const_parameter.ActionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
    private static final long serialVersionUID = 4381375727757464524L;

    private CommandRepository commandRepository;

    @Override
    public void init() throws ServletException {
        commandRepository = CommandRepositoryImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String commandName = req.getParameter(ActionType.COMMAND.getName());
        String commandName = req.getContextPath();

        CommandHandler handler = commandRepository.getCommandHandler(ActionType.valueOf(commandName));

        handler.handle(req, resp);
    }

}
