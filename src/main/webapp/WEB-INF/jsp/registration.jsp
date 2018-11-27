<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value=""/>

<html lang="en">
    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    </head>
    <body>


    <h2>Registration form </h2>

    <sf:form method="POST" modelAttribute="newUser" action="new">

        <table>
            <tr>
                <td>Username:</td>
                    <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="userName" type="text" placeholder="Username"/></td>
            </tr>
            <tr>
                <td>First name:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="firstName" type="text" placeholder="First name"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="lastName" type="text" placeholder="Last name"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="email" type="email" placeholder="Email"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                    <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="password" type="password" placeholder="Password"/></td>
            </tr>

        </table>

        <input type="submit" VALUE="Save"/>

    </sf:form>

    </body>
    <footer></footer>
</html>
