<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Location List</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Locations</h1>
<a href="location?action=new" class="btn">Add New Location</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="location" items="${locations}">
        <tr>
            <td>${location.locationId}</td>
            <td>${location.locationName}</td>
            <td>${location.locationAddress}</td>
            <td>
                <a href="location?action=edit&id=${location.locationId}" class="btn">Edit</a>
                <a href="location?action=delete&id=${location.locationId}" class="btn" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

