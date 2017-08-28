<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

	<!-- CSS certain css style specifications taken from various StackOverflow posts -->
	<style>
		a, li{
			color: white;
		}
		h3, h5 {
			color: #3b5998;
			padding: 5px;
		}
		h6 {
			color: white;
			padding: 5px;
		}
	</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" style="background-color: #3b5998">
	<a class="navbar-brand" href="#">${pageContext.request.userPrincipal.name}</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<!-- JSP Tags -->
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="<c:url value='/wall' />" >Wall<span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:url value='/outstandingRequests' />" >Friend Requests</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:url value='/friendList' />" >View Friends</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:url value='/blockList' />" >View Blocked Users</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:url value='/edit/${pageContext.request.userPrincipal.name}' />" >Edit Profile</a>
			</li>
			<li>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h5>
						<a href="javascript:formSubmit()"> Logout</a>
					</h5>
				</c:if>
			</li>
		</ul>

		<c:url var="addAction" value="/search" ></c:url>
		<form:form name = "formName" action="${addAction}" modelAttribute="user" class="form-inline my-2 my-lg-0">

			<input type='text' name='search' class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"/>
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search Users</button>

		</form:form>
	</div>
	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
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


	</sec:authorize>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col" style="background-color: #3b5998">
			<h6>Username : ${name}</h6>
			<h6>Email : ${email}</h6>
			<h6>Age : ${age}</h6>
			<h6>Phone Number : ${phone}</h6>
			<h6>Country : ${country}</h6>
		</div>
		<div class="col-10">
			<c:if test="${!empty listUsers}">
				<table class="table table-hover table-sm">
					<c:forEach items="${listUsers}" var="user">
						<h5>${user.commentOwner.username} wrote</h5>
						<h3>${user.comment}</h3>
						<h5>on ${user.wallOwner.username}'s wall</h5>
						<br>
						<br>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty listUsers}">
				<h3>No Posts Yet!</h3>
			</c:if>
		</div>
	</div>

</div>


</body>
</html>