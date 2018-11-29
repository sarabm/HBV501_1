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
    <div class="transaction-list-item">
        <a href="/transaction/${transaction.id}">
            <div>
                <h3>Amount: ${transaction.amount} </h3>
                <h3>Description: ${transaction.descr} </h3>
            </div>
        </a>
    </div>

</c:forEach>
    </div>



    </div>
</div>
</body>



</html>