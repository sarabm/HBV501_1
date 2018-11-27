<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../navBar.jsp" />


<html lang="en">

<head>
    <title>Lil Bill</title>
</head>
<body>

<h1>All Transactions </h1>

<h3> From ${userName}'s account: </h3>
<p> Email : ${currUser.email} </p>

<br>
<p>___________________________________________</p>

<c:forEach var="transaction" items="${transactions}">
    <div>
        <a href="/transaction/${transaction.id}">
            <div>
                <h3>Amount : ${transaction.amount} </h3>
                <h3>Description : ${transaction.descr} </h3>
            </div>
        </a>
        <p>___________________________________________</p>
    </div>

</c:forEach>



<a href="/../..">Back to home page</a>
</body>



</html>