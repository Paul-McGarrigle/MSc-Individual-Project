<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>User Page</title>
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
		<%--<th width="80">Users ID</th>--%>
		<th width="120">Users Name</th>
		<th width="120">Users Password</th>
		<th width="120">Users Email</th>
		<th width="120">Users Age</th>
		<%--<th width="120">Users Gender</th>--%>
		<th width="120">Users Birthday</th>
		<th width="120">Users Phone No</th>
		<th width="120">Users Country</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
		<th width="60">Add</th>
	</tr>
	<c:forEach items="${listUsers}" var="user">
		<tr>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>${user.email}</td>
			<td>${user.age}</td>
			<%--<td>${user.gender}</td>--%>
			<td>${user.birthday}</td>
			<td>${user.phone}</td>
			<td>${user.country}</td>
			<td><a href="<c:url value='/edit/${user.username}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${user.username}' />" >Delete</a></td>
			<td><a href="<c:url value='/addFriend/${user.username}' />" >Add</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<c:if test="${empty listUsers}">
	<h3>No Users Returned</h3>
</c:if>
</body>
</html>
