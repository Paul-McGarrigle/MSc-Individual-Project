<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 23/08/2017
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>User Wall</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}

        .button {
            display: block;
            width: 115px;
            height: 25px;
            background: #4E9CAF;
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            color: white;
            font-weight: bold;
        }
    </style>
</head>
<body>
<br>
<h3>Users List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="120">Comment Owner</th>
            <th width="120">Comment</th>
            <th width="120">Wall Owner</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.commentOwner.username}</td>
                <td>${user.comment}</td>
                <td id = "owner">${user.wallOwner.username}</td>
                <td><a class="button" href="<c:url value='/remove/${user.commentOwner.username}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<c:if test="${empty listUsers}">
    <h3>No Users Returned</h3>
</c:if>
<br>


<c:url var="addAction" value="/addComment" ></c:url>

<form:form action="${addAction}" commandName="user">
    <table>
        <tr>
            <td>Type:</td>
            <%--<td><springForm:input path="comment" /></td>--%>
            <td><input type='text' name='comment' /></td>
            <td><springForm:errors path="comment" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Post"/>" />

            </td>
        </tr>
    </table>
</form:form>
</body>
<footer>
    <script>
        //document.getElementById("owner");
        //document.getElementById("owner").innerHTML = "Hello World"
        var x = document.getElementById("owner").valueOf();
        console.log(x);
    </script>
</footer>
</html>

