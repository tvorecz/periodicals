<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 09.01.2019
  Time: 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale.name}" scope="session" />

<fmt:bundle basename="property.title">
    <fmt:message key="page.search" var="title" scope="request" />
</fmt:bundle>

<html>
    <head>
        <c:import url="/WEB-INF/jsp/head/head.jsp" charEncoding="utf-8" />
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header/header.jsp" charEncoding="utf-8" />

        <fmt:bundle basename="property.page" prefix="search.">
            <div class="content">
                <div class="container">
                    <div class="col-md-4 w3ls_dresses_grid_left">
                        <div class="w3ls_dresses_grid_left_grid">
                            <h3><fmt:message key="form" /></h3>
                            <div class="w3ls_dresses_grid_left_grid_sub">
                                <div class="ecommerce_dres-type">
                                    <ul>
                                        <form action="/periodical/search" method="get">
                                            <li>
                                                <input type="text" name="keySearch" class="form-control"
                                                       placeholder="<fmt:message key="key" />" value="${keySearch}"/><br />
                                            </li>
                                            <li><b><fmt:message key="type" /></b></li>
                                            <li><select name="periodicalTypeId" class="form-control">
                                                <option value=""><fmt:message key="choose" /></option>
                                                <c:forEach var="item" items="${periodicalTypes}" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${item.id eq selectedPeriodicalTypeId}">
                                                            <option value="${item.id}" selected>${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select></li>
                                            <li><b><fmt:message key="theme" /></b></li>
                                            <li><select name="periodicalThemeId" class="form-control">
                                                <option value=""><fmt:message key="choose" /></option>
                                                <c:forEach var="item" items="${periodicalThemes}" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${item.id eq selectedPeriodicalThemeId}">
                                                            <option value="${item.id}" selected>${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select></li>
                                            <li><b><fmt:message key="s_type" /></b></li>
                                            <li><select name="subscriptionTypeId" class="form-control">
                                                <option value=""><fmt:message key="choose" /></option>
                                                <c:forEach var="item" items="${subscriptionTypes}" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${item.id eq selectedSubscriptionTypeId}">
                                                            <option value="${item.id}" selected>${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select></li>
                                            <li><b><fmt:message key="amount" /></b></li>
                                            <li><select name="amountOnPage" class="form-control">
                                                <c:choose>
                                                    <c:when test="${6 eq selectedAmountOnPage}">
                                                        <option value="6" selected>6</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="6">6</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${9 eq selectedAmountOnPage}">
                                                        <option value="9" selected>9</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="9">9</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${12 eq selectedAmountOnPage}">
                                                        <option value="12" selected>12</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="12">12</option>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${15 eq selectedAmountOnPage}">
                                                        <option value="15" selected>15</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="15">15</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select></li>
                                            <li>
                                                <button class="btn btn-info btn-block" type="submit" id="button-addon2">
                                                    <i class="fa fa-search" aria-hidden="true"><fmt:message
                                                            key="find" /></i>
                                                </button>
                                            </li>
                                        </form>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 col-sm-8">



                        <%--<div class="row">--%>
                            <%--<c:forEach var="item" items="${periodicals}" varStatus="counter">--%>
                                <%--<c:if test="${((counter - 1) mod 3) eq 0}">--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--</div>--%>
                                    <%--<br />--%>
                                    <%--<br />--%>
                                    <%--<div class="row">--%>
                                <%--</c:if>--%>
                                    <%--<div class="col-md-4 women-grids wp1 animated wow slideInUp" data-wow-delay=".5s">--%>
                                        <%--<a href="/periodical/${item.id}">--%>
                                            <%--<div class="product-img">--%>
                                                <%--<img src="${item.imagePath}" alt="" />--%>
                                            <%--</div>--%>
                                        <%--</a>--%>
                                        <%--<h4>${item.name}</h4>--%>
                                        <%--<h5>${item.type.name}</h5>--%>
                                    <%--</div>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                        <%--<br />--%>
                        <%--<br />--%>


                        <ul class="pagination">
                            <c:if test="${currentPage eq 1}">
                                <c:set var="disabledPrev" value="disabled" scope="page" />
                            </c:if>
                            <c:if test="${currentPage eq countPage}">
                                <c:set var="disabledNext" value="disabled" scope="page" />
                            </c:if>

                            <li class="page-item ${disabledPrev}">
                                <a class="page-link" href="${currentQueryLink}&page=${currentPage - 1}"><fmt:message
                                        key="previous" /></a>
                            </li>


                            <c:forEach var="item" items="${pageNumbers}" varStatus="status">
                                <c:if test="${item eq currentPage}">
                                    <c:set var="activePage" value="active" scope="page" />
                                </c:if>
                                <li class="page-item ${activePage}">
                                    <a class="page-link" href="${currentQueryLink}&page=${item}">${item}</a>
                                </li>
                            </c:forEach>

                            <li class="page-item ${disabledNext}">
                                <a class="page-link" href="${currentQueryLink}&page=${currentPage + 1}"><fmt:message
                                        key="next" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </fmt:bundle>


        <c:import url="/WEB-INF/jsp/footer/footer.jsp" charEncoding="utf-8" />
    </body>
</html>
