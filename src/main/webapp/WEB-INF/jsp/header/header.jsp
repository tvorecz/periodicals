<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 18.12.2018
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header>
    <fmt:bundle basename="property.header">
    <div class="header-top-w3layouts">
        <div class="container">
            <div class="col-md-6 logo-w3">
                <a href="/"><img src="/images/period_logo.png" alt=" " />
                    <h1>PERIODICALS</h1></a>
            </div>
            <div class="col-md-6 phone-w3l">
                <ul>
                    <li><span class="glyphicon glyphicon-user" aria-hidden="true"></span></li>

                    <c:choose>
                        <c:when test="${empty sessionScope.currentUserId}">
                            <li><a href="/login?target=${requestScope.get('path')}"><fmt:message key="user.login" /></a></li>
                            <li><a href="/register"><fmt:message key="user.reg" /></a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/profile"><fmt:message key="user.profile" /> ${sessionScope.userLogin}</a></li>
                        </c:otherwise>
                    </c:choose>


                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="header-bottom-w3ls">
        <div class="container">
            <div class="col-md-7 navigation-agileits">
                <nav class="navbar navbar-default">
                    <div class="navbar-header nav_2">
                        <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                                data-target="#bs-megadropdown-tabs">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav ">
                            <li class=" active"><a href="/" class="hyper "><span><fmt:message key="menu.home" /></span></a></li>
                            <li class="dropdown ">
                                <a href="#" class="dropdown-toggle  hyper" data-toggle="dropdown"><span> <fmt:message key="menu.periodicals" /> <b
                                        class="caret"></b></span></a>
                                <ul class="dropdown-menu multi">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">

                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.woman" /></a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.man" /></a>
                                                </li>
                                                <li><a href="#"> <i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.children" /></a></li>

                                            </ul>

                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">

                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.month" /></a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.quarter" /></a>
                                                </li>
                                                <li><a href="#"> <i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.half_year" /></a></li>

                                            </ul>

                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.full" /></a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.favorable" /></a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="periodicals.departmental" /></a>
                                                </li>

                                            </ul>
                                        </div>
                                    </div>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle hyper" data-toggle="dropdown"><span> <fmt:message key="menu.account" /> <b
                                        class="caret"></b></span></a>
                                <ul class="dropdown-menu multi multi1">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <c:choose>
                                                    <c:when test="${empty sessionScope.currentUserId}">
                                                        <li><a href="/login?target=${requestScope.get('path')}"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="account.login" /></a></li>
                                                        <li><a href="/register"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="account.reg" /></a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a href="/profile"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="account.profile" /></a></li>
                                                        <li><a href="/profile/subscriptions"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="account.subcribe" /></a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                                <li><a href="/cart"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="account.cart" /></a>
                                                </li>

                                            </ul>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle hyper" data-toggle="dropdown"><span> <fmt:message key="menu.language" /> <b
                                        class="caret"></b></span></a>
                                <ul class="dropdown-menu multi multi1">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <c:choose>
                                                    <c:when test="${sessionScope.currentLocale.name eq 'ru_RU'}">
                                                        <c:set var="disabledRu" value="pointer-events: none" scope="page"/>
                                                    </c:when>
                                                    <c:when test="${sessionScope.currentLocale.name eq 'en_EN'}">
                                                        <c:set var="disabledEn" value="pointer-events: none" scope="page"/>
                                                    </c:when>
                                                </c:choose>

                                                <li><a style="${pageScope.disabledRu}" href="${requestScope.get('path')}?command=changeLocale&locale=ru_RU"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="language.ru" /></a></li>
                                                <li><a style="${pageScope.disabledEn}" href="${requestScope.get('path')}?command=changeLocale&locale=en_EN"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="language.en" /></a>
                                                </li>

                                            </ul>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>

                            <%--<li><a href="about.html" class="hyper"><span>&&&About</span></a></li>--%>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="col-md-4 search-agileinfo">
                <form action="#" method="post">
                    <input type="search" name="Search" placeholder="<fmt:message key="into.search" />" required="">
                    <button type="submit" class="btn btn-default search" aria-label="Left Align">
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
            </div>
            <div class="col-md-1 cart-wthree">
                <a class="btn btn-light" href="/subscriber/cart" role="button">
                    <div class="fa fa-cart-arrow-down" aria-hidden="true">
                        <c:choose>
                            <c:when test="${sessionScope.cartItems ne null}">
                                ${sessionScope.sizeCart}
                            </c:when>
                        </c:choose>
                    </div>
                </a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    </fmt:bundle>
</header>
