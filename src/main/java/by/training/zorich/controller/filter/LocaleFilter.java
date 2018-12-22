package by.training.zorich.controller.filter;

import by.training.zorich.bean.UserLocale;
import by.training.zorich.controller.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private final UserLocale defaultLocale =  UserLocale.RU;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
                                                                                                                  IOException,
                                                                                                                  ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpSession currentSession = httpServletRequest.getSession();

        UserLocale currentLocale = (UserLocale) currentSession.getAttribute(SessionAttribute.CURRENT_LOCALE.getName());

        if(currentLocale == null) {
            currentSession.setAttribute(SessionAttribute.CURRENT_LOCALE.getName(), defaultLocale);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
