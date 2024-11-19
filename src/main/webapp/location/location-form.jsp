<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Location Form</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>${location != null ? "Edit Location" : "New Location"}</h1>
<form action="location" method="post">
    <input type="hidden" name="id" value="${location != null ? location.locationId : ''}">
    <div>
        <label for="locationName">Name:</label>
        <input type="text" id="locationName" name="locationName" value="${location != null ? location.locationName : ''}" required>
    </div>
    <div>
        <label for="locationAddress">Address:</label>
        <input type="text" id="locationAddress" name="locationAddress" value="${location != null ? location.locationAddress : ''}" required>
    </div>
    <div>
        <button type="submit" class="btn">${location != null ? "Update" : "Save"}</button>
        <a href="locations" class="btn">Cancel</a>
    </div>
</form>
</body>
</html>
