package by.training.zorich.controller.filter;

import by.training.zorich.bean.UserLocale;
import by.training.zorich.controller.CookieHandler;
import by.training.zorich.controller.CookieName;
import by.training.zorich.controller.SessionAttribute;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private final UserLocale defaultLocale = UserLocale.RU;

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

        if (currentLocale == null) {
            Cookie cookie = CookieHandler.extractCookie(httpServletRequest, CookieName.CURRENT_LOCALE.getName());

            if(cookie == null) {
                currentSession.setAttribute(SessionAttribute.CURRENT_LOCALE.getName(), defaultLocale);
                Cookie newCookie = new Cookie(CookieName.CURRENT_LOCALE.getName(), defaultLocale.getName());

                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

                httpServletResponse.addCookie(newCookie);
            } else {
                currentSession.setAttribute(SessionAttribute.CURRENT_LOCALE.getName(), UserLocale.getUserLocaleByName(cookie.getValue()));
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
