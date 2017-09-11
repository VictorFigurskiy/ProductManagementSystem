<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 16.08.2017
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Welcome!</title>
</head>
<body  style="background:whitesmoke">
<%--style="background-image: url(https://www.kinogallery.com/img/wallpaper/kinogallery-wallpaper-0037301001450903712-1440x900.jpg);"--%>
<div align="center" style="padding-top: 12%">
    <h2>Вы вошли как: <sec:authentication property="principal.username"/></h2>
    <form:form action="/product/list" method="get">
        <input type="submit" value="Список товаров">
    </form:form>
    <sec:authorize access="hasRole('ADMIN')">
        <form:form action="/user/list" method="get">
            <input type="submit" value="Список юзеров">
        </form:form>
    </sec:authorize>
    <form:form action="/logout" method="post">
        <input type="submit" value="logout">
    </form:form>
</div>
</body>
</html>
