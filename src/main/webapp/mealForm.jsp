<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>

<body>
<h2><a href="index.html">Home</a></h2>
<hr>
<h3>Edit meal</h3>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>DateTime:</dt>
        <dd><label>
            <input type="datetime-local" value="${meal.dateTime}" name="dateTime">
        </label></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><label>
            <input type="text" value="${meal.description}" name="description">
        </label></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><label>
            <input type="number" value="${meal.calories}" name="calories">
        </label></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
