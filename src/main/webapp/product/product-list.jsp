<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
<header>
    <h1>Product List</h1>
</header>
<a href="product-form.jsp" class="button">Add New Product</a>
<table>
    <thead>
    <tr>
        <th>Product Code</th>
        <th>Name</th>
        <th>Weight</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.productCode}</td>
            <td>${product.productName}</td>
            <td>${product.packedWeight}</td>
            <td>
                <a href="#">Edit</a> | <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
