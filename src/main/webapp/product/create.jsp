<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <h1>Add New Product</h1>
</header>

<form action="save" method="post" class="form">
    <div class="form-group">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>
    </div>

    <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="list.jsp" class="btn btn-secondary">Cancel</a>
    </div>
</form>
</body>
</html>
