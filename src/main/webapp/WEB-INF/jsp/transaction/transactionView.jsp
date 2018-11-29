<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../navBar.jsp" />

<html lang="en">


<head>
    <title>Lil Bill</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/transaction/transactionView.css"/>"/>
</head>
<body>
<div class="view-transaction-container">
    <div class=view-transaction>

        <h1>Transaction Info</h1>
        <h2>Amount : ${sign*transaction.amount} </h2>
        <h2>Description : ${transaction.descr} </h2>
        <h2>Date: ${transaction.date} </h2>
        <c:if test="${transaction.splitInfo.size() >= 2}">
            <div>
                <h3>This transaction created by ${transaction.splitInfo.get(1)}</h3>
                <h3>Splitting ${transaction.splitInfo.get(0)} evenly between </h3>
                <c:forEach var="user" items="${transaction.splitInfo}" varStatus="stat">
                    <c:if test="${!stat.first}">
                        <p>${user}</p>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>


        <div class="account-button-container">
            <div class="account-button">
                <a href="/account/${transaction.account.id}" >View Account</a>
            </div>
        </div>
</body>



</html>