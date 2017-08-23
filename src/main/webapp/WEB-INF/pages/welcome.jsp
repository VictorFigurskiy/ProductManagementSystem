<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 22.08.2017
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
<div align="center"><h1>Добро пожаловать</h1></div>

<div align="center">
    <form action="/main" method="get">
        <input type="submit" value="Залогинится" width="30">
    </form>
</div>
<div align="center">
    <form action="/registration" method="get">
        <input type="submit" value="Зарегистрироватся" width="30">
    </form>
</div>

</body>
</html>
