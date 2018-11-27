<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../navBar.jsp" />

<html lang="en">

<head>
    <title>Lil Bill</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/transactionNew.css"/>"/>
</head>
<body>

<h1>Create a new transaction</h1>

<%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
<%--that is added to the model that is passed to the view.--%>
<%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
<sf:form method="POST" modelAttribute="transaction" action="new">

    <table>
        <tr>
            <td> Amount:</td>
                <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
            <td><sf:input path="amount" type="number" placeholder="Enter amount"/></td>
        </tr>
        <tr>
            <td>Description:</td>
                <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
            <td><sf:textarea path="descr" type="text" placeholder="Write a description of the transaction"/></td>
        </tr>
        <tr>
            <td>Friends:</td>
                <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
            <td class="friendlist">
                <label for="frida">
                    <input type="checkbox" name="friend" id="frida">
                    <span>
                         Fríða
                    </span>
                </label>
                <label for="sara">
                    <input type="checkbox" name="friend" id="sara">
                    <span>
                         Sara
                    </span>
                </label>
                <label for="isak">
                    <input type="checkbox" name="friend" id="isak">
                    <span>
                         Ísak
                    </span>
                </label>
                <label for="pall">
                    <input type="checkbox" name="friend" id="pall">
                    <span>
                         Páll
                    </span>
                </label>
            </td>
        </tr>
    </table>

    <input type="submit" VALUE="Save"/>

</sf:form>

<a href="/../..">Back to home page</a>

</body>

</html>