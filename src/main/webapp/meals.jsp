<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/FormatLocalDateTime"%>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>List of meals</h2>
<table>
    <thead>
    <tr>
        <th>Description</th>
        <th>DateTime</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="mealToList" scope="request" type="java.util.List"/>
    <c:forEach var="mealTo" items="${mealToList}">
        <tr ${mealTo.excess ? 'class="excess"' : 'class="normal"'}>
            <td>${mealTo.description}</td>
            <td>${f:formatLocalDateTime(mealTo.dateTime)}</td>
            <td>${mealTo.calories}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
