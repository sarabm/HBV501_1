<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../navBar.jsp" />


<html lang="en">

<head>
    <title>Lil Bill</title>
</head>
<body>

<h1>You're accounts</h1>

<br>
<p>___________________________________________</p>

<c:forEach var="account" items="${accounts}">
    <div>
        <a href="/account/${account.id}">
            <div>
                <c:when test="${account.users[0]}.equals(${currUser})" >
                    <h3>${account.users[1].username}</h3>
                    <h3>Balance : ${account.netBalance}  </h3>
                </c:when>
                <c:otherwise>
                    <h3>${account.users[0].username}</h3>
                    <h3>Balance : ${-1*account.netBalance}  </h3>
                </c:otherwise>
            </div>
        </a>
        <p>___________________________________________</p>
    </div>

</c:forEach>

<h3>Find new friends</h3>
<c:if test="${not empty msg}">
    <p>${msg}</p>
</c:if>


<form action="all" method="post">
    <input type="text" name="friendUserName" prefix="friend Username"/>
    <input type="submit" VALUE="Add friend" onclick="form.action='all';"/>
</form>


<a href="/../..">Back to home page</a>
</body>



</html>