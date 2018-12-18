<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 18.12.2018
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/bootstrap/css/modern-business.css" rel="stylesheet" />
        <script src="/bootstrap/font-awesome/css/font-awesome.css" />
    </head>
    <body>
        <header>
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light" style="background-color: azure;">
                    <a href="/index.jsp" class="navbar-brand">
                        <img src="/images/period_logo.png" alt="Periodicals" width="50" height="50" />
                        <label>PERIODICALS</label>
                    </a>

                    <form class="form-inline" action="#" method="post">
                        <input type="email" class="form-control mr-sm-2" id="email" aria-label="E-mail">
                        <input type="password" class="form-control mr-sm-2" id="pwd">
                        <div class="form-check">
                            <label class="form-check-label mr-sm-2">
                                <input class="form-check-input mr-sm-2" type="checkbox">Запомнить
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary">Войти</button>
                    </form>
                </nav>
            </div>

            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light" style="background-color: azure;">
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-item nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
                            <a class="nav-item nav-link" href="#">Features</a>
                            <a class="nav-item nav-link" href="#">Pricing</a>
                        </div>
                    </div>

                    <form class="form-inline" action="#" method="get">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </nav>
            </div>

        </header>
    </body>
</html>
