<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html lang="en">

    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/account-overview.css"/>"/>
    </head>
    <body>

    <c:choose>
        <c:when test="${isUser1}" >
            <c:set var="sign" value="${-1}"/>
            <c:set var="friend" value="${account.user2}"/>
        </c:when>
        <c:otherwise>
            <c:set var="sign" value="${1}"/>
            <c:set var="friend" value="${account.user1}"/>
        </c:otherwise>
    </c:choose>

    <h2>${friend}:</h2>
    <div>
        <h3>Balance : ${sign * account.netBalance} </h3>
        <c:if test="${sign * account.netBalance < 0}">
            <a href="/payup">Pay Up</a>
        </c:if>
    </div>
    <p>___________________________________________</p>

    <c:forEach var="transaction" items="${account.transactionList}">
        <div>
            <a href="/transaction/${transaction.id}">
                <div>
                    <h4>Amount : ${sign * transaction.amount} </h4>
                    <h4>Description : ${transaction.descr} </h4>
                </div>
            </a>
            <p>___________________________________________</p>
        </div>

    </c:forEach>

     <a href="/../..">Click here to return home</a>
    </body>

    <footer></footer>
</html>
