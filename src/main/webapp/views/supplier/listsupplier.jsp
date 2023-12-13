<!-- index.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Suppliers List</title>
</head>
<body>
    <h1>List of Suppliers</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <!-- Add other necessary columns -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${Suppliers}" var="supplier">
                <tr>
                    <td>${supplier.id}</td>
                    <td>${supplier.name}</td>
                    <!-- Add other necessary columns -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
