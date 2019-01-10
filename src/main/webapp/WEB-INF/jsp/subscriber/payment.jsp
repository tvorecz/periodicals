<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 08.01.2019
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.payment" var="title" scope="request" />
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="payment.">
            <div class="check-out">
                <div class="container">
                    <div class="spec ">
                        <h3><fmt:message key="form" /> ${payment.id}</h3>
                        <div class="ser-t">
                            <b></b>
                            <span><i></i></span>
                            <b class="line"></b>
                        </div>
                        <c:choose>
                            <c:when test="${not empty param.message and param.message eq 'success'}">
                                <div class="alert alert-success" role="alert">
                                    <strong><fmt:message key="form.message.success" /></strong>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                    <table class="table ">
                        <tr>
                            <th class="t-head head-it "><fmt:message key="periodicals" /></th>
                            <th class="t-head"><fmt:message key="names" /></th>
                            <th class="t-head"><fmt:message key="subscriprions" /></th>
                            <th class="t-head"><fmt:message key="start" /></th>
                            <th class="t-head"><fmt:message key="end" /></th>
                            <th class="t-head"><fmt:message key="addresses" /></th>
                        </tr>

                        <c:forEach var="item" items="${userSubscriptions}" varStatus="status">
                            <tr class="cross">
                                <td class="t-data col-md-2">
                                    <a href="/periodical/${item.subscriptionVariant.periodical.id}">
                                        <img src="${item.subscriptionVariant.periodical.imagePath}" data-imagezoom="true" class="img-thumbnail" alt="">
                                    </a>

                                    <div class="clearfix" />
                                </td>
                                <td class="t-data">${item.subscriptionVariant.periodical.name}</td>
                                <td class="t-data">${item.subscriptionVariant.subscriptionType.name}</td>
                                <td class="t-data">${item.dateBegin}</td>
                                <td class="t-data">${item.dateEnd}</td>
                                <td class="t-data">${item.userAddress.address}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="input-group col-sm-3">
                        <span class="input-group-addon bg-warning"><fmt:message key="total" /> ${payment.amount}</span>
                    </div>
                    <br />
                    <div class="input-group col-sm-3">
                        <c:choose>
                            <c:when test="${not empty payment.payStatus and payment.payStatus eq true}">
                                <div class="alert alert-success" role="alert">
                                    <span class="input-group-addon bg-warning"><strong><fmt:message key="status" /> <fmt:message key="status.true" /></strong></span>
                                </div>
                            </c:when>
                            <c:when test="${not empty payment.payStatus and payment.payStatus eq false}">
                                <div class="alert alert-danger" role="alert">
                                    <span class="input-group-addon bg-warning"><strong><fmt:message key="status" /> <fmt:message key="status.false" /></strong></span>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>
