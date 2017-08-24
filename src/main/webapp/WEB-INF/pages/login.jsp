<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 23.08.2017
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login page</title>
</head>
<body style="background:whitesmoke">
<div style="padding-top: 12%">
    <table border="3" align="center" cellpadding="10">
        <tr>
            <td>
                <div align="center">
                    <c:url value="/j_spring_security_check" var="loginUrl"/>
                    <form action="${loginUrl}" method="post">
                        <table width="450" align="center" cellpadding="10">
                            <tr align="center">
                                <th colspan="2"><h2 align="center">Пожалуйста введите данные для входа:</h2></th>
                            </tr>
                            <tr align="center">
                                <th>Введите ваш Email:</th>
                                <td><input type="email" name="j_username" placeholder="Email address" required autofocus></td>
                            </tr>
                            <tr align="center">
                                <th>Введите ваш пароль:</th>
                                <td><input type="password" name="j_password" placeholder="Password" required autofocus></td>
                            </tr>
                            <tr align="center">
                                <td colspan="2">
                                    <button type="submit">Войти</button>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <div align="center">
                        <form action="/registration" method="get">
                            <input type="submit" value="Зарегистрироватся" width="30">
                        </form>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
