<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 24/08/2017
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
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
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}

        .button {
            display: block;
            width: 115px;
            height: 25px;
            background: #4E9CAF;
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            color: white;
            font-weight: bold;
        }
    </style>
</head>
<body>
<br>
<h3><b id = "userName" name = "userName"></b> Wall</h3>
<br>
<c:url var="addAction" value="/search" ></c:url>
<form:form name = "formName" action="${addAction}" modelAttribute="user">
    <table>
        <tr>
            <td>Type:</td>
                <%--<td><springForm:input path="comment" /></td>--%>
            <td><input type='text' name='search' /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Post"/>" />

            </td>
        </tr>
    </table>
    <c:if test="${!empty listFriends}">
        <table class="tg">
            <tr>
                <th width="120">Comment Owner</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listFriends}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td><a class="button" href="<c:url value='/remove/${z.username}' />" >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty listFriends}">
        <h3>No Users Returned</h3>
    </c:if>
</form:form>
<br>
<c:if test="${not empty searchResult}">
<h2>Results for ${searchTerm}:<h2>
    <c:forEach items="${searchResult}" var="result">
    <li>${result}</li>
    </c:forEach>
</c:if>
<div class="row">
    <form method="get" action="/searching">
        <div class="small-3 columns">
            <input type="text" id ="txt" name="searchString" >
        </div>

        <div class="small-5 columns end">
            <button type="submit">Search Teams</button>
        </div>

        <div>
            ${player.superTeam}
        </div>
    </form>

</div>
</body>
<footer>
    <script>
        //document.getElementById("owner");
        //document.getElementById("owner").innerHTML = "Hello World"
        //<script>document.write(x)close script tag
        //var x = document.getElementById("owner").firstChild.nodeValue;
        //var x = document.getElementsById("owner").firstChild.nodeValue;
        //console.log(x);
        //document.getElementById("userName").innerHTML = x;
        //document.getElementById("wallOwner").innerHTML = x;
    </script>
</footer>
</html>


