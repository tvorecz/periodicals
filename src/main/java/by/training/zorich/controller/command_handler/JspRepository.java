package by.training.zorich.controller.command_handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface JspRepository {
    void init(CommandRepository commandRepository);

    void readdressToJsp(HttpServletRequest request, HttpServletResponse response) throws
                                                                                  ServletException,
                                                                                  IOException;
}
