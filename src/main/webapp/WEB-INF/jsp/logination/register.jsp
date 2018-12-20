<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 03.12.2018
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="${sessionScope.get(currentLocale)}" />--%>
<fmt:setLocale value="ru_RU" />

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="resources.property.page" prefix="registration.">
            <div class="login">

                <div class="main-agileits">
                    <div class="form-w3agile">
                        <h3>&&&Register</h3>
                        <form action="/SignUp" method="post">
                            <div class="key">
                                <input type="hidden" name="command" value="/SignUp" />
                                <i class="fa fa-user" aria-hidden="true"></i>

                                <fmt:message key="username" var="username"/>

                                <input type="text" name="login" required="" placeholder="${username}">
                                <div class="clearfix"></div>
                            </div>
                            <div class="key">
                                <i class="fa fa-envelope" aria-hidden="true"></i>

                                <fmt:message key="email" var="email"/>

                                <input type="text" name="email" required="" placeholder="${email}">
                                <div class="clearfix"></div>
                            </div>
                            <div class="key">
                                <i class="fa fa-lock" aria-hidden="true"></i>

                                <fmt:message key="password" var="password"/>

                                <input type="password" name="password" required="" placeholder="${password}">
                                <div class="clearfix"></div>
                            </div>
                            <div class="key">
                                <i class="fa fa-lock" aria-hidden="true"></i>

                                <fmt:message key="confirm" var="confirm"/>

                                <input type="password" name="Confirm Password" required=""
                                       placeholder="${confirm}">
                                <div class="clearfix"></div>
                            </div>

                            <fmt:message key="submit" var="submit"/>

                            <input type="submit" value="${submit}">
                        </form>
                    </div>
                </div>
            </div>

        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>
