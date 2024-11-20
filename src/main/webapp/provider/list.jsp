<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11/20/2024
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Provider List</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Provider List</h1>
    <a href="create.jsp" class="btn btn-primary">Add New Provider</a>
</header>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Contact Email</th>
        <th>Phone</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="provider" items="${providers}">
        <tr>
            <td>${provider.id}</td>
            <td>${provider.name}</td>
            <td>${provider.email}</td>
            <td>${provider.phone}</td>
            <td>
                <a href="update.jsp?id=${provider.id}" class="btn btn-edit">Edit</a>
                <a href="delete?id=${provider.id}" class="btn btn-delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
