<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 22.08.2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>
<div align="center">
    <form method="post" action="/register">
        <div>
            <label>Имя пользователя</label>
            <input type="text" id="firstName" name="firstName" size="30" maxlength="30">
        </div>
        <div>
            <label>Фамилия пользователя</label>
            <input type="text" id="lastName" name="lastName" size="30" maxlength="30">
        </div>
        <div>
            <label>Пароль</label>
            <input type="password" id="password" name="password" size="30" maxlength="30">
        </div>
        <div>
            <label>Email</label>
            <input type="email" id="email" name="email" size="30" maxlength="30">
        </div>
        <input type="submit" width="30" value="Зарегистрировать">
    </form>
</div>
</body>
</html>
