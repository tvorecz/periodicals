package by.training.zorich.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieHandler {
    public static Cookie extractCookie(HttpServletRequest httpServletRequest, String name) {
        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }
}
