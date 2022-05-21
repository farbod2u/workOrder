<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Work Order</title>
</head>
<body>
<h1>Validation</h1>
<form:form action="/validation-history" method="post" modelAttribute="workOrderModel">

    <table>
        <tr>
            <td align="right"><form:label path="json">Work order JSON : </form:label></td>
            <td>
                <form:textarea rows="10" cols="100" path="json"/>
            </td>
        </tr>
        <tr>
            <td align="right"><form:label path="result">Result : </form:label></td>
            <td>
                <form:textarea rows="10" cols="100" readonly="true" path="result"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Validate"/></td>
        </tr>
    </table>
</form:form>
<h1>History</h1>
<a href="http://localhost:8080/validation-history/getall">Validation History</a>
</body>
</html>