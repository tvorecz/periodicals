<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 03.12.2018
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <div id="register_form">
        <form action="/SignUp" method="post">
            <input type="hidden" name="command" value="/SignUp"/>
            Enter login:<br/>
            <input type="text" name="login" value=""/></br>
            Enter password:<br/>
            <input type="password" name="password" value=""/></br>
            Enter email:<br/>
            <input type="email" name="email" value=""/></br>
            <input type="submit" value="Зарегистрироваться"/>
        </form>
    </div>
</body>
</html>
