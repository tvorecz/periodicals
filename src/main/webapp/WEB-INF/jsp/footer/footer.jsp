<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 18.12.2018
  Time: 1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<footer>
    <fmt:bundle basename="property.footer">
    <div class="footer">
        <div class="container">
            <div class="col-md-3 footer-grids fgd1">
                <a href="/"><img src="/images/period_logo.png" alt=" " />
                    <h3>PERIODICALS</h3></a>
                <ul>
                    <li><fmt:message key="address.city" /></li>
                    <li><fmt:message key="address.street" /></li>
                    <li><a href="mailto:tvorecz@gmail.com">tvorecz@gmail.com</a></li>
                    <a href="https://t.me/tvorecz"><i class="fa fa-paper-plane" aria-hidden="true"></i></a>
                    <a href="https://www.facebook.com/tvorecz"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                    <a href="https://www.linkedin.com/in/tvorecz/"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                    <a href="https://twitter.com/tvorecz"><i class="fa fa-twitter" aria-hidden="true"></i></a>

                </ul>
            </div>
            <div class="col-md-3 footer-grids fgd2">
                <h4><fmt:message key="information.info" /></h4>
                <ul>
                    <li><a href="#"><fmt:message key="information.about" /></a></li>
                    <li><a href="#"><fmt:message key="information.contact" /></a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-grids fgd3">
                <h4><fmt:message key="periodical.periodicals" /></h4>
                <ul>
                    <li><a href="#"><fmt:message key="periodical.catalog" /></a></li>
                    <li><a href="#"><fmt:message key="periodical.month" /></a></li>
                    <li><a href="#"><fmt:message key="periodical.quarter" /></a></li>
                    <li><a href="#"><fmt:message key="periodical.half_year" /></a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-grids fgd4">
                <h4><fmt:message key="account.my" /></h4>
                <ul>
                    <c:choose>
                        <c:when test="${empty sessionScope.currentUserId}">
                            <li><a href="/login?target=${requestScope.get('path')}"><fmt:message key="account.login" /></a></li>
                            <li><a href="/register"><fmt:message key="account.register" /></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/profile"><fmt:message key="account.profile" /></a></li>
                            <li><a href="/profile/subscriptions"><fmt:message key="account.subscription" /></a></li>
                        </c:otherwise>
                    </c:choose>

                    <li><a href="/cart"><fmt:message key="account.cart" /></a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
            <p class="copy-right"><fmt:message key="site.info" /> <a
                    href="http://w3layouts.com">
                W3layouts.</a></p>
        </div>
    </div>
    </fmt:bundle>
</footer>
