<%--
  Created by IntelliJ IDEA.
  User: tvore
  Date: 03.12.2018
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти в систему</title>
</head>
<body>
<div id="enter_form">
    <form action="/SignIn" method="post">
        <input type="hidden" name="command" value="/SignIn"/>
        Enter login:<br/>
        <input type="text" name="login" value=""/></br>
        Enter password:<br/>
        <input type="password" name="password" value=""/></br>
        <input type="submit" value="Войти в систему"/>
    </form>

</div>
</body>
</html>
