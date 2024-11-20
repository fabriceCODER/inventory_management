<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/20/2024
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Edit Customer</h1>
</header>

<form action="update" method="post" class="form">
    <input type="hidden" name="id" value="${customer.id}">

    <div class="form-group">
        <label for="name">Customer Name:</label>
        <input type="text" id="name" name="name" value="${customer.name}" required>
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${customer.email}" required>
    </div>

    <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${customer.phone}" required>
    </div>

    <div class="form-group">
        <label for="address">Address:</label>
        <textarea id="address" name="address" required>${customer.address}</textarea>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>
