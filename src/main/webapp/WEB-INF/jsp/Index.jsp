<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="navBar.jsp" />


<html lang="en">
    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/Index.css"/>"/>
    </head>
    <body>

    <div class="transaction-list-container">

        <div class="transaction-list-inner">

            <h1>Hello username! </h1>

            <h3> Recent transactions</h3>
            <div class="transaction-list">
                <div class="transaction-list-item">
                    <a href="/transaction/${transaction.id}">
                        <div>
                            <h3>Amount: {transaction.amount} </h3>
                            <h3>${transaction.descr} </h3>
                        </div>
                    </a>
                </div>
                <div class="transaction-list-item">
                    <a href="/transaction/${transaction.id}">
                        <div>
                            <h3>Amount: {transaction.amount} </h3>
                            <h3>{transaction.descr} </h3>
                        </div>
                    </a>
                </div>
            </div>
            <div class="all-trans-button-container">
                <div class="all-trans-button">
                    <a href="/transaction/all">All transactions</a>
                </div>
            </div>
        </div>
    </div>

    </body>
    <footer></footer>
</html>
