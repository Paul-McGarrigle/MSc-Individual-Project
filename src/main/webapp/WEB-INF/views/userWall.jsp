<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 23/08/2017
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>User Wall</title>
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
            height: 25px;
            background: #4E9CAF;
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
<h3><b id = "userName" name = "userName"></b> Wall</h3>
<c:url var="addAction" value="/addComment" ></c:url>

<form:form name = "formName" action="${addAction}" commandName="user">
    <table>
        <table align="center">
            <tr>
                <td><input type='text' name='comment' /></td>
                <td><springForm:errors path="comment" /></td>
                <td colspan="2">
                    <input class = "button" type="submit" value="<spring:message text="Post"/>" />
                </td>
            </tr>
        </table>
    </table>
</form:form>
<c:if test="${!empty listUsers}">
    <table class="tg" align="center">
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
    <h3>No Posts Yet</h3>
</c:if>
<br>

</body>
<footer>
    <script>
        var x = document.getElementById("owner").firstChild.nodeValue;
        console.log(x);
        document.getElementById("userName").innerHTML = x;
        document.getElementById("wallOwner").innerHTML = x;
    </script>
</footer>
</html>

