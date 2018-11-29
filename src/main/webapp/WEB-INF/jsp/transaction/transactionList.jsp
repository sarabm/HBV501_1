<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../navBar.jsp" />


<html lang="en">

<head>
    <title>Lil Bill</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/transaction/transactionList.css"/>"/>
</head>
<body>
<div class="transaction-list-container">

    <div class="transaction-list-inner">

        <h1>All Transactions </h1>

        <div class="transaction-list">

            <c:forEach var="transaction" items="${transactions}">

                <c:set var="isUser1" value="${transaction.account.user1 == currUser.username}"/>
                <c:choose>
                    <c:when test="${isUser1}" >
                        <c:set var="sign" value="${1}"/>
                        <c:set var="friend" value="${transaction.account.user2}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="sign" value="${-1}"/>
                        <c:set var="friend" value="${transaction.account.user1}"/>
                    </c:otherwise>
                </c:choose>
                <c:set var="className" value="plus"/>
                <c:if test="${sign*transaction.amount < 0}">
                    <c:set var="className" value="minus"/>
                </c:if>

                <div class="transaction-list-item">
                    <div>
                        <a href="/account/${transaction.account.id}">
                            <h2>${friend}</h2>
                        </a>
                        <a href="/transaction/${transaction.id}">
                            <h3 class="${className}"> ${sign*transaction.amount}</h3>
                            <p> ${transaction.date} </p>
                        </a>

                    </div>
                </div>

            </c:forEach>
        </div>



    </div>
</div>
</body>



</html>