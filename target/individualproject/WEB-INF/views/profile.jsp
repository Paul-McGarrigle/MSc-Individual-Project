<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	<style>
		a, li{
			color: white;
		}
	</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #3b5998">
	<a class="navbar-brand" href="#">${pageContext.request.userPrincipal.name}</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

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
			<h6>Title : ${title}</h6>
			<h6>Message : ${message}</h6>
			<h6>Title : ${title}</h6>
			<h6>Message : ${message}</h6>
			<h6>Title : ${title}</h6>
			<h6>Message : ${message}</h6>
			<h6>Title : ${title}</h6>
			<h6>Message : ${message}</h6>
			<h6>Title : ${title}</h6>
			<h6>Message : ${message}</h6>
		</div>
		<div class="col-10">
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
				<c:url var="addAction" value="/addComment" ></c:url>

				<form:form name = "formName" action="${addAction}" commandName="user">
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
			</c:if>
			<c:if test="${empty listUsers}">
				<h3>No Users Returned</h3>
			</c:if>
		</div>
	</div>

</div>


</body>
</html>