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
<body style="background:whitesmoke">
<div align="center" style="padding-top: 12%">
    <h3 align="center">Форма регистрации пользователя</h3>
    <form method="post" action="/register">
        <table align="center">
            <tr align="center">
                <th align="left">Имя пользователя</th>
                <td><input type="text" id="firstName" name="firstName" size="30" maxlength="30"></td>
            </tr>
            <tr align="center">
                <th align="left">Фамилия пользователя</th>
                <td><input type="text" id="lastName" name="lastName" size="30" maxlength="30"></td>
            </tr>
            <tr align="center">
                <th align="left">Email</th>
                <td><input type="email" id="email" name="email" size="30" maxlength="30"></td>
            </tr>
            <tr align="center">
                <th align="left">Пароль</th>
                <td><input type="password" id="password" name="password" size="30" maxlength="30"></td>
            </tr>
            <tr align="center">
            <td colspan="2"><input type="submit" width="30" value="Зарегистрировать"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
