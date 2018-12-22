<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 21.12.2018
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.login" var="title" scope="request" />
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="logination.">
        <div class="login">
            <div class="main-agileits">
                <div class="form-w3agile">
                    <h3><fmt:message key="form"/></h3>
                    <form action="/login/done" method="post">
                        <input type="hidden" name="command" value="login" />
                        <div class="key">
                            <i class="fa fa-user" aria-hidden="true"></i>
                            <input type="text" name="login" required="" placeholder="<fmt:message key="login"/>">
                            <div class="clearfix"></div>
                        </div>
                        <div class="key">
                            <i class="fa fa-lock" aria-hidden="true"></i>
                            <input type="password" name="password" required="" placeholder="<fmt:message key="password"/>">
                            <div class="clearfix"></div>
                        </div>
                        <input type="submit" value="<fmt:message key="submit"/>">
                        <a href="/register" class="btn btn-primary forg-right" role="link"><fmt:message key="register"/></a>
                    </form>
                </div>
            </div>
        </div>
        </fmt:bundle>

        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>