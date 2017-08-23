<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 17.08.2017
  Time: 15:42
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
    <title>Products</title>
</head>
<body>
<div>
    <h2 align="center">Таблица товаров</h2>
    <table border="1" width="800" align="center" cellpadding="10">
        <tr align="center">
            <th>Наименование</th>
            <th>Описание</th>
            <th>Производитель</th>
            <th>Цена</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th>Удалить</th>
                <th>Изменить</th>
            </sec:authorize>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr align="center">
                <td>${product.name}</td>
                <td>${product.descriptions}</td>
                <td>${product.manufacturer}</td>
                <td>${product.price}</td>
                <sec:authorize access="hasRole('ADMIN')">
                <td>
                    <form action="/product/delete${product.id}" method="get"><input type="submit" value="Удалить"></form>
                </td>
                <td>
                    <form action="/product/update_product${product.id}" method="get"><input type="submit"
                                                                                            value="Изменить"></form>
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</div>
<sec:authorize access="hasRole('ADMIN')"> This is only admins button</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
    <div>
        <form action="${pageContext.request.contextPath}/product/validate" method="get">
            <input type="submit" value="Добавить новый продукт"/>
        </form>
    </div>
</sec:authorize>
<form action="${pageContext.request.contextPath}/main" method="get"><input type="submit" value="Назад на главную"></form>
</body>
</html>
