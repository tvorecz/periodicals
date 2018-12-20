<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 18.12.2018
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="header-top-w3layouts">
        <div class="container">
            <div class="col-md-6 logo-w3">
                <a href="/index.jsp"><img src="/images/period_logo.png" alt=" " />
                    <h1>PERIODICALS</h1></a>
            </div>
            <div class="col-md-6 phone-w3l">
                <ul>
                    <li><span class="glyphicon glyphicon-user" aria-hidden="true"></span></li>
                    <li><a href="login.html">&&Login</a></li>
                    <li><a href="register.html">&&&Register</a></li>
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
                            <li class=" active"><a href="index.html" class="hyper "><span>&&&Home</span></a></li>
                            <li class="dropdown ">
                                <a href="#" class="dropdown-toggle  hyper" data-toggle="dropdown"><span> &&&Periodicals <b
                                        class="caret"></b></span></a>
                                <ul class="dropdown-menu multi">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">

                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Month</a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Quarter</a>
                                                </li>
                                                <li><a href="#"> <i class="fa fa-angle-right" aria-hidden="true"></i>&&&Half
                                                    a year</a></li>

                                            </ul>

                                        </div>
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Full</a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Favorable</a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Departmental</a>
                                                </li>

                                            </ul>
                                        </div>
                                    </div>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle hyper" data-toggle="dropdown"><span> &&&Personal <b
                                        class="caret"></b></span></a>
                                <ul class="dropdown-menu multi multi1">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Login
                                                </a></li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Register</a>
                                                </li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&My
                                                    Subscriptions</a></li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Cart</a>
                                                </li>

                                            </ul>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle hyper" data-toggle="dropdown"><span> &&&Language <b
                                        class="caret"></b></span></a>
                                <ul class="dropdown-menu multi multi1">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <ul class="multi-column-dropdown">
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&Russian
                                                </a></li>
                                                <li><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>&&&English</a>
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
            <script>
                $(document).ready(function () {
                    $(".dropdown").hover(
                        function () {
                            $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                            $(this).toggleClass('open');
                        },
                        function () {
                            $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                            $(this).toggleClass('open');
                        }
                    );
                });
            </script>
            <div class="col-md-4 search-agileinfo">
                <form action="#" method="post">
                    <input type="search" name="Search" placeholder="$$$Search for a Product..." required="">
                    <button type="submit" class="btn btn-default search" aria-label="Left Align">
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
            </div>
            <div class="col-md-1 cart-wthree">
                <div class="cart">
                    <form action="#" method="post" class="last">
                        <input type="hidden" name="cmd" value="_cart" />
                        <input type="hidden" name="display" value="1" />
                        <button class="w3view-cart" type="submit" name="submit" value=""><i
                                class="fa fa-cart-arrow-down" aria-hidden="true"></i></button>
                    </form>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

</header>
