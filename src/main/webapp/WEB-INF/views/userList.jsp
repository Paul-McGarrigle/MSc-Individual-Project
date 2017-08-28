<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>User Page</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

	<!-- CSS certain css style specifications taken from various StackOverflow posts -->
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
			height: 40px;
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
<h2><a href="<c:url value='/welcome' />" >Back to your Profile!</a></h2>
<h1>Search Results</h1>

<!-- JSP Form Tags -->
<c:if test="${!empty listUsers}">
	<table class="tg" align="center">
	<tr>
		<%--<th width="80">Users ID</th>--%>
		<th width="120">Users Name</th>
		<th width="120">Users Email</th>
		<th width="120">Users Age</th>
		<th width="120">Users Phone No</th>
		<th width="120">Users Country</th>
		<th width="60">Add</th>
	</tr>
	<c:forEach items="${listUsers}" var="user">
		<tr>
			<td>${user.username}</td>
			<td>${user.email}</td>
			<td>${user.age}</td>
			<td>${user.phone}</td>
			<td>${user.country}</td>
			<td><a class="button" href="<c:url value='/addFriend/${user.username}' />" >Send Friend Request</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<c:if test="${empty listUsers}">
	<h3>No Users With that name</h3>
</c:if>
<c:if test="${!empty friendName}">
	<h3>You are already friends with ${friendName}</h3>
</c:if>
<c:if test="${pageContext.request.userPrincipal.name == friendName}">
	<h3>You are already friends with yourself</h3>
</c:if>

</body>
</html>
