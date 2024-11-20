<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Edit Product</h1>
</header>

<form action="update" method="post" class="form">
    <input type="hidden" name="id" value="${product.id}">

    <div class="form-group">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required>
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${product.description}</textarea>
    </div>

    <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${product.price}" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>
