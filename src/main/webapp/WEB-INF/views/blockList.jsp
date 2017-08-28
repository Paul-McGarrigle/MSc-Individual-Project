<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 21/08/2017
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<html>
<head>
    <!-- CSS certain css style specifications taken from various StackOverflow posts -->
    <title>Outstanding Friend Requests</title>
    <style type="text/css">
        h1, h2, h3, h5{
            color:white;
            text-align: center;

        }

        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}

        .button {
            display: block;
            width: 115px;
            height: 20px;
            background: #3b5998;
            padding: 5px;
            text-align: center;
            border-radius: 5px;
            color: white;
            font-weight: bold;
        }
    </style>
</head>
<body style="background-color: #3b5998">
<br>
<!-- JSP Tags -->
<h2><a href="<c:url value='/welcome' />" >Back to your Profile!</a></h2>
<h3>Blocked Users List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg" align="center">
        <tr>
            <th width="120">Users Name</th>
            <th width="60">Block</th>
        </tr>
        <c:forEach items="${listUsers}" var="x">
            <tr>
                <td>${x.user1.username}</td>
                <td><a class="button" href="<c:url value='/unBlockUser/${x.user1.username}' />" >Unblock</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty listUsers}">
    <h3>You have not blocked any users</h3>
</c:if>
</body>
</html>

