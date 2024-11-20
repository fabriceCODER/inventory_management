<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/20/2024
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory List</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Inventory List</h1>
    <a href="create.jsp" class="btn btn-primary">Add New Inventory</a>
</header>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Warehouse</th>
        <th>Product</th>
        <th>Stock Level</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="inventory" items="${inventories}">
        <tr>
            <td>${inventory.id}</td>
            <td>${inventory.warehouse.name}</td>
            <td>${inventory.product.name}</td>
            <td>${inventory.stockLevel}</td>
            <td>
                <a href="update.jsp?id=${inventory.id}" class="btn btn-edit">Edit</a>
                <a href="delete?id=${inventory.id}" class="btn btn-delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

</html>
