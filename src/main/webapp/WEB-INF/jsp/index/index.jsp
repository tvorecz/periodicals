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
    <fmt:message key="page.index" var="title" scope="request"/>
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="main.">
            <div class="banner-agile" style="background: url(/images/banner1.jpg) no-repeat 0px 0px">
                <div class="container">
                    <h2><fmt:message key="form" /></h2>
                    <h3><fmt:message key="here" /> <span><fmt:message key="call" /></span></h3>
                    <a href="/periodical/search"><fmt:message key="catalog" /></a>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>
