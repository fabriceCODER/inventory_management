<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Inventory</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Add New Inventory</h1>
</header>

<form action="save" method="post" class="form">
    <div class="form-group">
        <label for="warehouse">Warehouse:</label>
        <select id="warehouse" name="warehouseId" required>
            <c:forEach var="warehouse" items="${warehouses}">
                <option value="${warehouse.id}">${warehouse.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="product">Product:</label>
        <select id="product" name="productId" required>
            <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="stockLevel">Stock Level:</label>
        <input type="number" id="stockLevel" name="stockLevel" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>
