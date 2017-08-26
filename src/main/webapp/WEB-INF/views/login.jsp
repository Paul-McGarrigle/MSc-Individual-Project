<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<!-- Javascript -->
	<%--script <src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- CSS -->
	<link rel="stylesheet" href="/WEB-INF/views/assets/css/bootstrap.min.css">--%>
	<%--<link rel="stylesheet" href="assets/css/style1.css">--%>
	<link type="text/css" href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	<%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style1.css" />--%>
	<%--<link rel="stylesheet" href="assets/css/formplexy-bootstrap-dark.css">
	<link rel="stylesheet" href="assets/css/iconfont.css">--%>

	<title>Login Page</title>
	<style>
		h1, h2{
			color:white;
			text-align: center;
			margin-top: 50px;
		}

		.error {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #a94442;
			background-color: #f2dede;
			border-color: #ebccd1;
		}

		.msg {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #31708f;
			background-color: #d9edf7;
			border-color: #bce8f1;
		}

		#login-box {
			width: 400px;
			padding: 20px;
			margin: 150px auto;
			background: #fff;
			-webkit-border-radius: 2px;
			-moz-border-radius: 2px;
			border: 1px solid #000;
		}
	</style>
</head>
<body onload='document.loginForm.username.focus();' style="background-color: #3b5998">
<%--<div class = "jumbotron">Hello World</div>--%>

	<h1>Login to your account</h1>

	<div id="login-box">

		<h3>Login with your Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'>

			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" class="btn btn-primary" style="background-color: #3b5998"/></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>

<h2>Don't have an account? Register <a href="<c:url value='/register' />" >here!</a></h2>

</body>
</html>