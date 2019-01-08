<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 07.01.2019
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.cart" var="title" scope="request" />
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="cart.">
            <div class="check-out">
                <div class="container">
                    <div class="spec ">
                        <h3><fmt:message key="form" /></h3>
                        <div class="ser-t">
                            <b></b>
                            <span><i></i></span>
                            <b class="line"></b>
                        </div>
                    </div>
                    <table class="table ">
                        <tr>
                            <th class="t-head head-it "><fmt:message key="periodicals" /></th>
                            <th class="t-head"><fmt:message key="names" /></th>
                            <th class="t-head"><fmt:message key="subscriprions" /></th>
                            <th class="t-head"><fmt:message key="costs" /></th>
                            <th class="t-head"><fmt:message key="options" /></th>
                        </tr>

                        <c:forEach var="item" items="${subscriptionVariants}" varStatus="status">
                            <tr class="cross">
                                <td class="t-data">
                                    <a href="/periodical/${item.periodical.id}">
                                        <img src="${item.periodical.imagePath}" data-imagezoom="true" class="img-thumbnail" alt="">
                                    </a>

                                    <div class="clearfix" />
                                </td>
                                <td class="t-data">${item.periodical.name}</td>
                                <td class="t-data">${item.subscriptionType.name}</td>
                                <td class="t-data">${item.actualCost}</td>


                                <td class="t-data">
                                    <form action="/subscriber/cart/delete" method="post">
                                        <input type="hidden" name="command" value="deleteCartItem" />
                                        <input type="hidden" name="idSubscriptionVariant" value="${item.id}" />
                                        <button type="submit" class="btn btn-danger"><fmt:message
                                                key="delete" /></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="input-group col-sm-3">
                        <span class="input-group-addon bg-warning"><fmt:message key="total" />${totalCost}</span>
                    </div>
                    <br />
                    <div>
                        <form action="/subscriber/subscribe" method="post">
                            <input type="hidden" name="command" value="subscribe" />
                            <c:forEach var="item" items="${subscriptionVariants}" varStatus="status">
                                <input type="hidden" name="subscriptionVariants" value="${item.id}" />
                            </c:forEach>
                            <div class="input-group">
                                <span class="input-group-addon"><fmt:message key="addresses" /></span>
                                <select name="idAddress" class="form-control">
                                    <c:forEach var="item" items="${userAddresses}" varStatus="status">
                                        <option value="${item.id}">${item.address}</option>
                                    </c:forEach>
                                    <%--<option>${item.name}</option>--%>
                                </select>
                                <span class="input-group-btn"><button type="submit" class="btn btn-success"><fmt:message
                                        key="subscribe" /></button></span>
                            </div>
                        </form>
                    </div>
                    <br />
                    <div>
                        <form action="/subscriber/address/add" method="post">
                            <input type="hidden" name="command" value="addAddress" />
                            <div class="input-group">
                                <input name="newAddress" type="text" class="form-control"
                                       placeholder="<fmt:message key="add.info" />">
                                <span class="input-group-btn"><button type="submit" class="btn btn-success"><fmt:message
                                        key="add.address" /></button></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>
