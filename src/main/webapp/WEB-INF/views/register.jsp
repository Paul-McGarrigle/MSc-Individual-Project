<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 26/07/2017
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

    <title>Register</title>

    <!-- CSS certain css style specifications taken from various StackOverflow posts -->
    <style>
        h1, h2{
            color:white;
            text-align: center;
            margin-top: 50px;
        }

        #reg-box {
            width: 400px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }

        .error {
            color: #3b5998;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body style="background-color: #3b5998">
<h1>
    Register to create an Account!
</h1>

<!-- JSP Form Tags -->
<c:url var="addAction" value="/user/add" ></c:url>

<form:form action="${addAction}" commandName="user" class="form-group" id="reg-box">
    <table>
        <c:if test="${!empty user.username}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" class="form-control"/>
                    <form:hidden path="id" />
                </td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><form:input path="username" readonly="true" class="form-control"/>
                <td><springForm:errors path="username" cssClass="error" /></td>
                </td>
            </tr>
        </c:if>
        <c:if test="${empty user.username}">
            <tr>
                <td>Username:</td>
                <td><form:input path="username" class="form-control"/>
                <td><springForm:errors path="username" cssClass="error" /></td>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Password:</td>
            <td><springForm:input path="password" class="form-control"/></td>
            <td><springForm:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><springForm:input path="email" class="form-control"/></td>
            <td><springForm:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><springForm:input path="age" class="form-control"/></td>
            <td><springForm:errors path="age" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><springForm:input path="birthday" placeholder="MM/dd/yyyy" class="form-control"/></td>
            <td><springForm:errors path="birthday" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><springForm:input path="phone" class="form-control" /></td>
            <td><springForm:errors path="phone" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><springForm:input path="country" class="form-control" /></td>
            <td><springForm:errors path="country" cssClass="error" /></td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty user.username}">
                    <input type="submit" class="btn btn-primary" style="background-color: #3b5998"
                           value="<spring:message text="Edit"/>" />
                </c:if>
                <c:if test="${empty user.username}">
                    <input type="submit" class="btn btn-primary" style="background-color: #3b5998"
                           value="<spring:message text="Register"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

<h2>Return to Login page <a href="<c:url value='/login' />" >here!</a></h2>
</body>
</html>
