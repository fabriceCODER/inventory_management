<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order List</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<header>
    <h1>Order List</h1>
    <a href="create.jsp" class="btn btn-primary">Add New Order</a>
</header>

<table class="table">
    <thead>
    <tr>
        <th>Order ID</th>
        <th>Customer</th>
        <th>Order Date</th>
        <th>Total Amount</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.customer.name}</td>
            <td>${order.orderDate}</td>
            <td>${order.totalAmount}</td>
            <td>
                <a href="update.jsp?id=${order.id}" class="btn btn-edit">Edit</a>
                <a href="delete?id=${order.id}" class="btn btn-delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
