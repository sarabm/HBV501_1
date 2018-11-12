<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html lang="en">


    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    </head>
    <body>

    <h1>Lil Bill</h1>
    <p>Hi there!</p>
    <p>Welcome to Lil Bill, your go-to transaction management service. Here, the idea is that you can keep track of how much you owe your friends and how much they owe you.</p>
    <p>So convenient!</p>
    <p>But please bear with us as functionality is not completely here yet. Happy logging :)</p>

    <a href="/transaction/new">Click here to create a new transaction</a>
    <br>
    <a href="/transaction">Click here to view all transactions</a>

    </body>
    <footer></footer>
</html>
