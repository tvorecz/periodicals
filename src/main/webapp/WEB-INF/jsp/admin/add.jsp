<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 03.01.2019
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.add" var="title" scope="request" />
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="addition.">
            <div class="add">

                <div class="main-agileits">
                    <div class="form-w3agile">
                        <h3><fmt:message key="form" /></h3>
                        <br />

                        <c:choose>
                            <c:when test="${not empty param.message and param.message eq 'dbError'}">
                                <div class="alert alert-success" role="alert">
                                    <strong><fmt:message key="form.message.dbError" /></strong><br />
                                    <fmt:message key="form.message.dbErrorCall" />
                                </div>
                                <br />
                            </c:when>
                        </c:choose>

                        <form action="/admin/added" method="post" enctype="multipart/form-data" acceptcharset="UTF-8" >
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="name" /></span>
                                <input name="namePeriodical" type="text" class="form-control" />
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="description" /></span>
                                <input name="annotation" type="text" class="form-control" />
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="periodicity" /></span>
                                <input name="periodicityInMonth" type="text" class="form-control" />
                                <span class="input-group-addon"><fmt:message key="month" /></span>
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="type" /></span>
                                <select name="idType" class="form-control">
                                    <c:forEach var="item" items="${periodicalTypeList}" varStatus="status">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="theme" /></span>
                                <select name="idTheme" class="form-control">
                                    <c:forEach var="item" items="${periodicalThemeList}" varStatus="status">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <input name="image" type="file" class="form-control-file" aria-describedby="fileHelp"
                                       placeholder="">
                                <small id="fileHelp" class="form-text text-muted"><fmt:message key="image" /></small>
                            </div>
                            <br />
                            <h4><fmt:message key="subscriptions" /></h4>
                            <br />
                            <c:forEach var="item" items="${subscriptionTypeList}" varStatus="status">
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <input name="idSubscriptionVariant${item.id}" value="${item.id}" type="checkbox" />
                                </span>
                                    <span class="input-group-addon">${item.name}</span>
                                    <input name="costForIssue${item.id}" type="text" class="form-control"
                                           placeholder="<fmt:message key="cost" />" />
                                    <input name="indexSubscription${item.id}" type="text" class="form-control"
                                           placeholder="<fmt:message key="index" />" />
                                    <span class="input-group-addon"><fmt:message key="currency" /></span>
                                </div>
                                <br />
                            </c:forEach>
                            <input type="submit" value="<fmt:message key="add" />">
                        </form>
                    </div>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>