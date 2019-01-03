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

                        <form action="/admin/added" method="post">
                            <input type="hidden" name="command" value="add" />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="name" /></span>
                                <input name="name" type="text" class="form-control" />
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="description" /></span>
                                <input name="description" type="text" class="form-control" />
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="periodicity" /></span>
                                <input name="periodicity" type="text" class="form-control" />
                                <span class="input-group-addon"><fmt:message key="month" /></span>
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="type" /></span>
                                <select name="type" class="form-control">
                                    <option selected><fmt:message key="selected" /></option>
                                    <c:forEach>


                                    </c:forEach>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </select>
                            </div>
                            <br />
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"><fmt:message key="theme" /></span>
                                <select name="theme" class="form-control">
                                    <option selected><fmt:message key="selected" /></option>
                                    <c:forEach>


                                    </c:forEach>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
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
                            <c:forEach>
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" />
                                </span>
                                    <span class="input-group-addon">Месячная</span>
                                    <input name="subscription?????" type="text" class="form-control"
                                           placeholder="<fmt:message key="cost" />" />
                                    <span class="input-group-addon"><fmt:message key="currency" /></span>
                                </div>
                            </c:forEach>
                            <br />
                            <input type="submit" value="Добавить">
                        </form>
                    </div>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>