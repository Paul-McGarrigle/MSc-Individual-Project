<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
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
	<h1>HTTP Status 403 - Access is denied</h1>

	<c:choose>
		<c:when test="${empty username}">
			<h2>You need an Admin account to access thia page, you are unregistered</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${username} <br/>You need an Admin account to access this page, your account is basic user</h2>
			<br>
			<h2><a href="<c:url value='/welcome' />" >Back to your Profile!</a></h2>
		</c:otherwise>
	</c:choose>

</body>
</html>