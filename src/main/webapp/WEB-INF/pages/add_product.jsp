<%--
  Created by IntelliJ IDEA.
  User: Sonikb
  Date: 17.08.2017
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>new product</title>
</head>
<body>
<div>
    <h3>Для добавления товаров:</h3>
    <form:form modelAttribute="productFromPage" method="post" action="/product/save">
        <p><label>Введите название продукта:<br>
            <form:input path="name" size="30" maxlength="30"/></label></p>
        <p><label>Введите короткое описание:<br>
            <form:input path="descriptions" size="30" maxlength="255"/></label></p>
        <p><label>Производитель продукта:<br>
            <form:input path="manufacturer" size="30" maxlength="30"/></label></p>
        <p><label>Цена:<br>
            <form:input path="price" size="30" maxlength="30"/></label></p>
        <p><form:button>Добавить</form:button></p>
    </form:form>
</div>
</body>
</html>
