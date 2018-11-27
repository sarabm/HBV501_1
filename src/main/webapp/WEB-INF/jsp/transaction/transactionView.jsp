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

<h1>Transaction submitted</h1>
<h2>Amount : ${transaction.amount} </h2>
<h2>Description : ${transaction.descr} </h2>


<a href="/../..">Back to home page</a>
</body>



</html>