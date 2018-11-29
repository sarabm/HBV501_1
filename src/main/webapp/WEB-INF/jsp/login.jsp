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

<div class="navbar-container">
    <ul class="navbar">
        <div class="navbar--item navbar__left">
            <li><a href="/">Lil Bill</a></li>
        </div>
        <div class="navbar--item navbar__right">
            <li class="button"><a href="/registration">Register</a></li>
        </div>
    </ul>
</div>



<div class="main-site-container">
    <div class="main-info">
        <c:if test="${not empty msg}">
            <p>${msg}</p>
        </c:if>
        <h1>Hi there!</h1>
        <p>
            Welcome to Lil Bill, your go-to transaction management service. You
            can create shared account with friends to keep track of how much you
            owe each other and settle bills periodically instead of constant money
            transfers.
        </p>
        <div class="form-login-container">
            <div class="form-login">
                <c:if test="${not empty errorMsg}">
                    <p>${errorMsg}</p>
                </c:if>
                <form method="POST" action="/login" class="form-signin">
                    <h2 class="form-login-heading">Log In</h2>

                    <div class="form-group">
                        <span></span>
                        <input
                                name="username"
                                type="text"
                                class="form-input form-control"
                                placeholder="Username"
                                autofocus="true"
                        />
                        <input
                                name="password"
                                type="password"
                                class="form-input form-control"
                                placeholder="Password"
                                required=""
                        />
                        <span></span>

                        <button class="form-login-button" type="submit">Log In</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="registration-button-container">
            <div class="registration-button-inner">
                <p>Don't have an account?</p>
                <div class="registration-button">
                    <a href="/registration">Register</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<footer></footer>
</html>
