<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 02/08/2017
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>HTTP Status 500 - That Username is already in use</h1>
<!-- Jsp Tags -->
<c:choose>
    <c:when test="${empty username}">
        <h2>You do not have permission to access this page!</h2>
    </c:when>
    <c:otherwise>
        <h2>Username : ${username} <br/>You do not have permission to access this page!</h2>
    </c:otherwise>
</c:choose>

</body>
</html>
