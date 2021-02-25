<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxmlv
  Date: 25/02/2021
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <h1>All photos: </h1>
    </br>
    <form action="/delete_checked" method="post">
        <c:forEach var="id" items="${photosId}">
            <input type="checkbox" name="checked[]" value="${id}"> ${id}
                <img src="/photo/${id}" width="50px" height="50px"></br>
        </c:forEach>
        </br>
        <input type="submit" value="Delete photos">
    </form>
    <form action="/" method="post">
        </br>
        <input type="submit" value="Back">
    </form>
</div>
</body>
</html>
