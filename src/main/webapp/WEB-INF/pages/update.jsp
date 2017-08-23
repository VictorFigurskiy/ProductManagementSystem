<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 18.08.2017
  Time: 13:31
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
    <title>update</title>
</head>
<body>
<div>
    <h2 align="center">Таблица товаров</h2>
    <table border="1" width="800" align="center" cellpadding="10">
        <form:form action="/product/update" method="post" modelAttribute="updateProduct">
            <input hidden name="id" value="${product.id}">
            <tr align="center">
                <th>Наименование</th>
                <td>${product.name}</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr align="center">
                <th>Описание</th>
                <td>${product.descriptions}</td>
                <td><form:input path="descriptions"/></td>
            </tr>
            <tr align="center">
                <th>Производитель</th>
                <td>${product.manufacturer}</td>
                <td><form:input path="manufacturer"/></td>
            </tr>
            <tr align="center">
                <th>Цена</th>
                <td>${product.price}</td>
                <td><form:input path="price"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <div align="center"><form:button>Изменить</form:button></div>
                </td>
            </tr>
        </form:form>
    </table>
</div>
</body>
</html>
