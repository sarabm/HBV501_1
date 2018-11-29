<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="navBar.jsp" />

<c:set var="contextPath" value=""/>


<html lang="en">
    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/registration.css"/>"/>
    </head>
    <body>
    <div class="navbar-container">
        <ul class="navbar">
            <div class="navbar--item navbar__left">
                <li >
                    <a href="/">Lil Bill</a>
                </li>
            </div>
            <div class="navbar--item navbar__right">
                <li class="button">
                    <a href="/login">Log In</a>
                </li>

            </div>
        </ul>
    </div>


    <div class="registration-container">
            <div class="registration-inner">
        <h2>Register</h2>

    <c:if test="${not empty errors}">
        <c:forEach var="error" items="${errors}">
            <p>${error}</p>
        </c:forEach>

    </c:if>
    <c:if test="${not empty success}">
        <p>${success}</p>
        <a href="/login">Go to login</a>
    </c:if>

    <sf:form method="POST" modelAttribute="newUser" action="registration">

        <table>
            <tr>
                <td>Username:</td>
                    <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                <td><sf:input class="form-input" path="username" type="text" placeholder="Username" required=""/></td>
            </tr>
            <tr>
                <td>First name:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input class="form-input" path="firstname" type="text" placeholder="First name" required=""/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input class="form-input" path="lastname" type="text" placeholder="Last name" required=""/></td>
            </tr>
            <tr>
                <td>Email:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input class="form-input" path="email" type="email" placeholder="Email" required=""/></td>
            </tr>
            <tr>
                <td>Password:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input class="form-input" path="password" type="password" placeholder="Password" required=""/></td>
            </tr>

        </table>

        <input class="form-registration-button"  type="submit" VALUE="Register"/>

    </sf:form>
            </div>
        </div>
    </body>
    <footer></footer>
</html>
