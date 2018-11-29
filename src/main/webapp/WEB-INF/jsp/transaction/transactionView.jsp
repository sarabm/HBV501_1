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
<h2>Amount : ${transaction.amount} </h2>
<h2>Description : ${transaction.descr} </h2>
        <h2>Date: ${transaction.date} </h2>
        <div class="account-button-container">
            <div class="account-button">
                <a href="/account/${transaction.getAccount().getId()}" >View Account</a>
            </div>
        </div>
</body>



</html>