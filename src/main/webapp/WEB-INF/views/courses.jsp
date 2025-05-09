<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Courses</title>
    <style>
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
<h2>Available Courses</h2>
<a href="/logout">Logout</a>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<c:if test="${not empty success}">
    <p class="success">${success}</p>
</c:if>

<table border="1">
    <tr>
        <th>Name</th><th>Instructor</th><th>Credits</th><th>Action</th>
    </tr>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.name}</td>
            <td>${course.instructor}</td>
            <td>${course.credits}</td>
            <td>
                <form method="post" action="/register/${course.courseId}">
                    <input type="submit" value="Register">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>