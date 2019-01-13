<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 03.12.2018
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.registration" var="title" scope="request"/>
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="registration.">
            <div class="login">

                <div class="main-agileits">
                    <div class="form-w3agile">
                        <h3><fmt:message key="form" /></h3>
                        <ctg:message messageType="${param.message}">
                            <fmt:message key="${messageForUser}" />
                        </ctg:message>
                        <form name="registerForm" action="/register/done" method="post">

                            <input type="hidden" name="command" value="register" />

                            <div class="key">
                                <i class="fa fa-user" aria-hidden="true"></i>

                                <input type="text" name="login" required="" placeholder="<fmt:message key="username"/>">
                                <div class="clearfix"></div>
                            </div>
                            <div class="key">
                                <i class="fa fa-envelope" aria-hidden="true"></i>



                                <input type="text" name="email" required="" placeholder="<fmt:message key="email" />">
                                <div class="clearfix"></div>
                            </div>
                            <div class="key">
                                <i class="fa fa-lock" aria-hidden="true"></i>

                                <input type="password" name="password" required="" placeholder="<fmt:message key="password" />">
                                <div class="clearfix"></div>
                            </div>
                            <div class="key">
                                <i class="fa fa-lock" aria-hidden="true"></i>

                                <input type="password" name="confirm" required=""
                                       placeholder="<fmt:message key="confirm" />">
                                <div class="clearfix"></div>
                            </div>
                            <input class="btn btn-success center-block" onclick="RegisterUser();" type="submit" value="<fmt:message key="submit" />">
                        </form>
                    </div>
                </div>
            </div>

        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>
