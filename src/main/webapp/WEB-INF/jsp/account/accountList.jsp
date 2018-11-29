<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../navBar.jsp" />


<html lang="en">

<head>
    <title>Lil Bill</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/account/accountList.css"/>"/>
</head>
<body>
<div class="account-list-container">

    <div class="account-list-inner">

        <h1>Accounts </h1>

        <div class="account-list">
<c:forEach var="account" items="${accounts}">
    <div class="account-list-item">
        <a href="/account/${account.id}">
            <div>
                <c:set var="curr" value="${currUser.username}"/>
                <c:set var="u1" value="${account.user1}"/>
                <c:choose>
                    <c:when test="${curr == u1}" >
                        <h3>${account.user2}</h3>
                        <h3>Balance : ${account.netBalance}  </h3>
                    </c:when>
                    <c:otherwise>
                        <h3>${account.user1}</h3>
                        <h3>Balance : ${-1*account.netBalance}  </h3>
                    </c:otherwise>
                </c:choose>
            </div>
        </a>
    </div>

</c:forEach>
        </div>
    </div>
    <div class="find-friends">
<h2>Find new friends</h2>
<c:if test="${not empty msg}">
    <p>${msg}</p>
</c:if>


<form action="all" method="post">
    <input class="form-input" type="text" name="friendUserName" prefix="friend Username"/>
    <input class="button" type="submit" VALUE="Add friend" onclick="form.action='all';"/>
</form>

    </div>
</div>
</body>



</html>