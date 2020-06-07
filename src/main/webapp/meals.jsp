<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/FormatLocalDateTime" prefix="f" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<table>
    <caption>List of meals</caption>
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
        <tr
                <c:choose>
                    <c:when test="${mealTo.isExcess() == true}">
                        class="excess"
                    </c:when>

                    <c:when test="${mealTo.isExcess() == false}">
                        class="normal"
                    </c:when>
                </c:choose>
        >
            <td>${mealTo.getDescription()}</td>
            <td>${f:formatLocalDateTime(mealTo.getDateTime(),"dd.MM.yyyy HH:mm")}</td>
            <td>${mealTo.getCalories()}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
