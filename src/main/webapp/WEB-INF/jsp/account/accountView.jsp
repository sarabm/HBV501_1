<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../navBar.jsp" />

<html lang="en">

    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/account/accountView.css"/>"/>
    </head>
    <body>
    <div class="transaction-list-container">

        <div class="transaction-list-inner">

    <c:choose>
        <c:when test="${isUser1}" >
            <c:set var="sign" value="${1}"/>
            <c:set var="friend" value="${account.user2}"/>
        </c:when>
        <c:otherwise>
            <c:set var="sign" value="${-1}"/>
            <c:set var="friend" value="${account.user1}"/>
        </c:otherwise>
    </c:choose>

    <h2>Account with ${friend}</h2>
            <div class="transaction-list">
    <c:forEach var="transaction" items="${transactions}">
        <div class="transaction-list-item">
            <a href="/transaction/${transaction.id}">
                <div>
                    <h4>Amount : ${sign * transaction.amount} </h4>
                    <h4>Description : ${transaction.descr} </h4>
                </div>
            </a>
        </div>

    </c:forEach>
        </div>
        </div>
        <div class="balance-info">
            <h2>Balance</h2>
            <h2>${sign * account.netBalance} </h2>
            <c:if test="${sign * account.netBalance < 0}">
            <div class="button">
                <a href="/account/${account.id}/payup">Pay Up</a>
            </div>
            </c:if>
        </div>
    </div>
    </body>

    <footer></footer>
</html>
