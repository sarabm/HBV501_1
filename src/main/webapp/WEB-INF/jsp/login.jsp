<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<html lang="en">
    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    </head>
    <body>

    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>


    <h1>Lil Bill</h1>
    <p>Hi there!</p>
    <p>Welcome to Lil Bill, your go-to transaction management service. Here, the idea is that you can keep track of how much you owe your friends and how much they owe you.</p>
    <p>So convenient!</p>
    <p>But please bear with us as functionality is not completely here yet. Happy logging :)</p>
    <p>Login below or register here: </p>
    <a href="/registration">Click here to register</a>
    <br>

    <div class="loginForm">
        <c:if test="${not empty errorMsg}">
            <p>${errorMsg}</p>
        </c:if>

        <form method="POST" action="/login" class="form-signin">
            <h2 class="form-heading">Log in</h2>

            <div class="form-group ">
                <span></span>
                <input name="username" type="text" class="form-control" placeholder="Username"
                       autofocus="true" required=""/>
                <input name="password" type="password" class="form-control" placeholder="Password" required=""/>
                <span></span>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            </div>

        </form>

    </div>

    </body>
    <footer></footer>
</html>
