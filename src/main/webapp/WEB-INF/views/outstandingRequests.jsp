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
    <title>Outstanding Friend Requests</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<br>
<h3>Users List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="120">Users Name</th>
            <th width="60">Accept</th>
            <th width="60">Decline</th>
            <th width="60">Block</th>
            <th width="60">Friends In Common</th>
        </tr>
        <c:forEach items="${listUsers}" var="x">
            <tr>
                <td>${x.user1.username}</td>
                <td><a href="<c:url value='/acceptFriendRequest/${x.user1.username}' />" >Accept</a></td>
                <td><a href="<c:url value='/declineFriendRequest/${x.user1.username}' />" >Decline</a></td>
                <td><a href="<c:url value='/blockUser/${x.user1.username}' />" >Block</a></td>
                <td><a href="<c:url value='/wall/${x.user1.username}' />" >See wall</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty listUsers}">
    <h3>No Users Returned</h3>
</c:if>
</body>
</html>

