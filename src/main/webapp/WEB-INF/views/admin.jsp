<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<!-- Ideas for page taken from http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/ -->
<head>
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
	<h1>${message}</h1>

	<!-- JSP Form Tags -->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

	<h2><a href="<c:url value='/welcome' />" >Back to your Profile!</a></h2>
	<h3>Users List</h3>
	<c:if test="${!empty listUsers}">
		<table class="tg">
			<tr>
				<th width="120">Users Name</th>
				<th width="120">Users Password</th>
				<th width="120">Users Email</th>
				<th width="120">Users Age</th>
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
					<td>${user.birthday}</td>
					<td>${user.phone}</td>
					<td>${user.country}</td>
					<td><a class="button" href="<c:url value='/edit/${user.username}' />" >Edit</a></td>
					<td><a class="button" href="<c:url value='/remove/${user.username}' />" >Delete</a></td>
					<td><a class="button" href="<c:url value='/addFriend/${user.username}' />" >Add</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty listUsers}">
		<h3>No Users Returned</h3>
	</c:if>
</body>

</body>
</html>