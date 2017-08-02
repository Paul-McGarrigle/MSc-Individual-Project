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
    <title>Register</title>
</head>
<body>
<h1>
    Add a User
</h1>

<style type="text/css">
    .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
    .tg .tg-4eph{background-color:#f9f9f9}

    .error {
        color: #ff0000;
        font-style: italic;
        font-weight: bold;
    }
</style>

<c:url var="addAction" value="/user/add" ></c:url>

<form:form action="${addAction}" commandName="user">
    <table>
        <c:if test="${!empty user.username}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Username:</td>
            <td><form:input path="username" />
            <td><springForm:errors path="username" cssClass="error" /></td>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><springForm:input path="password" /></td>
            <td><springForm:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><springForm:input path="email" /></td>
            <td><springForm:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><springForm:input path="age" /></td>
            <td><springForm:errors path="age" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td><springForm:select path="gender">
                <springForm:option value="" label="Select Gender" />
                <springForm:option value="MALE" label="Male" />
                <springForm:option value="FEMALE" label="Female" />
            </springForm:select></td>
            <td><springForm:errors path="gender" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><springForm:input path="birthday" placeholder="MM/dd/yyyy"/></td>
            <td><springForm:errors path="birthday" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><springForm:input path="phone" /></td>
            <td><springForm:errors path="phone" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><springForm:input path="country" /></td>
            <td><springForm:errors path="country" cssClass="error" /></td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty user.username}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>" />
                </c:if>
                <c:if test="${empty user.username}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
