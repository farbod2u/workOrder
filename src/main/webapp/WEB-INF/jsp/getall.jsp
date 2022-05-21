<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Work Order</title>
</head>
<body>
<h1>Validation History</h1>
<a href="http://localhost:8080/validation-history">Back</a>
<table border="1">
    <thead>
    <tr>
        <th>Receive Date</th>
        <th>Type</th>
        <th>Department</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${workOrders}" var="wo">
        <tr>
            <td>${wo.receive_date}</td>
            <td>${wo.type}</td>
            <td>${wo.department}</td>
            <td>${wo.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>