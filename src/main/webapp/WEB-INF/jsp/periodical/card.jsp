<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 05.01.2019
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.card" var="title" scope="request"/>
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="card.">
            <div class="products">
                <div class="container">
                    <div class="single-page">
                        <div class="single-page-row" id="detail-21">
                            <div class="col-md-6 single-top-left">
                                <img src="${periodical.imagePath}" data-imagezoom="true" class="img-responsive" alt="">
                            </div>
                            <div class="col-md-6 single-top-right">
                                <h3 class="item_name">${periodical.name}</h3>

                                <%--<c:choose>--%>
                                    <%--<c:when test="${not empty param.message and param.message eq 'dbSuccess'}">--%>
                                        <%--<div class="alert alert-success" role="alert">--%>
                                            <%--<strong><fmt:message key="form.message.addToDB" /></strong><br />--%>
                                        <%--</div>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${not empty param.message and param.message eq 'cartSuccess'}">--%>
                                        <%--<div class="alert alert-success" role="alert">--%>
                                            <%--<strong><fmt:message key="form.message.addToCart" /></strong><br />--%>
                                        <%--</div>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${not empty param.message and param.message eq 'cartError'}">--%>
                                        <%--<div class="alert alert-danger" role="alert">--%>
                                            <%--<strong><fmt:message key="form.message.cartError" /></strong><br />--%>
                                        <%--</div>--%>
                                    <%--</c:when>--%>
                                <%--</c:choose>--%>

                                <ctg:message messageType="${param.message}">
                                    <fmt:message key="${messageForUser}" />
                                </ctg:message>

                                <div class="single-rating">
                                    <ul>
                                        <li><fmt:message key="theme" /><p>${periodical.theme.name}</p>
                                        </li>
                                        <li><fmt:message key="type" /><p>${periodical.type.name}</p>
                                        </li>
                                        <li><fmt:message key="periodicity" /><p>${periodical.periodicityInMonth}</p>
                                        </li>
                                    </ul>
                                </div>
                                <div>
                                    <h3 class="item_name"><fmt:message key="annotation" /></h3>
                                    <p class="single-price-text">${periodical.annotation}</p>
                                </div>
                                <div>
                                    <form action="/subscriber/cart/add" method="post">
                                        <input type="hidden" name="command" value="addToCart" />
                                        <input type="hidden" name="returnPath" value="/periodical/${periodical.id}?message=cartSuccess"/>
                                        <div class="input-group">
                                            <span class="input-group-addon"><fmt:message key="subscriptions" /></span>
                                            <select name="type" class="form-control">
                                                <c:forEach var="item" items="${subscriptionVariants}" varStatus="status">
                                                    <option value="${item.id}">${item.subscriptionType.name} — ${item.actualCost} <fmt:message key="currency" /></option>
                                                </c:forEach>
                                            </select>
                                            <%--<c:choose>--%>
                                                <%--<c:when test="${sessionScope.userRole.name ne 'подписчик' and sessionScope.userRole.name ne 'администратор'}">--%>
                                                    <%--<c:set var="disabledButton" value="disabled" scope="page" />--%>
                                                <%--</c:when>--%>
                                            <%--</c:choose>--%>
                                            <span class="input-group-btn"><button type="submit" class="btn btn-secondary" ${pageScope.disabledButton}><i class="fa fa-cart-plus" aria-hidden="true"></i> <fmt:message key="add" /></button></span>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>