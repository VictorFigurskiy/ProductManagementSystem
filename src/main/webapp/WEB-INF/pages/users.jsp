<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 17.08.2017
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Users</title>
</head>
<body>
<div>
    <h2 align="center">Таблица пользователей в системе</h2>
    <table border="1" width="800" align="center" cellpadding="10">
        <tr align = "center">
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Email</th>
            <th>Админ</th>
            <th>Удаление пользователей</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr align = "center">
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.admin ? "Да" : "Нет"}</td>
                <td><form action="${pageContext.request.contextPath}/user/delete${user.id}" method="get"><input type="submit" value="Удалить"/></form></td>
            </tr>
        </c:forEach>
    </table>
</div>
<form action="../" method="get"><input type="submit" value="Назад на главную"></form>
</body>
</html>
<%--<td><a href="${pageContext.request.contextPath}/delete?taskid=${task.id}">Delete</a></td>--%>
