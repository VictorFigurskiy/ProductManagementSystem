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
<body>
<div align="right">
    <form action="/registration" method="get">
        <input type="submit" value="Зарегистрироватся" width="30">
    </form>
</div>
<div align="center">
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <h2 align="center">Пожалуйста введите данные для логина:</h2>
        <h4 align="center">Введите ваш Email:</h4>
        <input type="email" name="j_username" placeholder="Email address" required autofocus value="email@email.com">
        <h4 align="center">Введите ваш пароль:</h4>
        <input type="password" name="j_password" placeholder="Password" required autofocus value="1234"><br>
        <button type="submit">Войти</button>
    </form>
</div>

</body>
</html>
