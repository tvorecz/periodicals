package by.training.zorich.controller.command_handler;

import by.training.zorich.controller.command_handler.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandHandler {
    void handle(HttpServletRequest request, HttpServletResponse response) throws
                                                                          ServletException,
                                                                          IOException,
                                                                          CommandException;
}
