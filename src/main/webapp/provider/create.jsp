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
    <title>Add Provider</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Add New Provider</h1>
</header>

<form action="save" method="post" class="form">
    <div class="form-group">
        <label for="name">Provider Name:</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>
