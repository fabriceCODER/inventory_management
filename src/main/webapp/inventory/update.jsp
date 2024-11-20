<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/20/2024
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Inventory</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Edit Inventory</h1>
</header>

<form action="update" method="post" class="form">
    <input type="hidden" name="id" value="${inventory.id}">

    <div class="form-group">
        <label for="warehouse">Warehouse:</label>
        <select id="warehouse" name="warehouseId" required>
            <c:forEach var="warehouse" items="${warehouses}">
                <option value="${warehouse.id}"
                        <c:if test="${warehouse.id == inventory.warehouse.id}">selected</c:if>>${warehouse.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="product">Product:</label>
        <select id="product" name="productId" required>
            <c:forEach var="product" items="${products}">
                <option value="${product.id}"
                        <c:if test="${product.id == inventory.product.id}">selected</c:if>>${product.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="stockLevel">Stock Level:</label>
        <input type="number" id="stockLevel" name="stockLevel" value="${inventory.stockLevel}" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>

</html>
