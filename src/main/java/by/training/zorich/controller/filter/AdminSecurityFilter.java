package by.training.zorich.controller.filter;

import by.training.zorich.bean.UserRole;
import by.training.zorich.controller.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSecurityFilter implements Filter {
    private final static UserRole DEFAULT_USER_ROLE = UserRole.GUEST;
    private final static String ROOT = "/";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
                                                                                                                  IOException,
                                                                                                                  ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession currentSession = httpServletRequest.getSession();

        UserRole currentUserRole = (UserRole) currentSession.getAttribute(SessionAttribute.CURRENT_USER_ROLE.getName());

        if (UserRole.ADMIN.equals(currentUserRole)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (currentUserRole == null) {
                currentUserRole = DEFAULT_USER_ROLE;
                currentSession.setAttribute(SessionAttribute.CURRENT_USER_ROLE.getName(), currentUserRole);
            }

            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(ROOT);
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
