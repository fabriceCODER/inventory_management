<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/20/2024
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Order</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Edit Order</h1>
</header>

<form action="update" method="post" class="form">
    <input type="hidden" name="id" value="${order.id}">

    <div class="form-group">
        <label for="customer">Customer:</label>
        <select id="customer" name="customerId" required>
            <c:forEach var="customer" items="${customers}">
                <option value="${customer.id}"
                        <c:if test="${customer.id == order.customer.id}">selected</c:if>>${customer.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" value="${order.orderDate}" required>
    </div>

    <div class="form-group">
        <label for="totalAmount">Total Amount:</label>
        <input type="number" id="totalAmount" name="totalAmount" value="${order.totalAmount}" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>
