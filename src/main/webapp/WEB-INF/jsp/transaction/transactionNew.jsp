<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <title>Lil Bill</title>
</head>
<body>

<h1>New Transaction - page</h1>
<p> --- haha nice ---- </p>

<%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
<%--that is added to the model that is passed to the view.--%>
<%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
<sf:form method="POST" modelAttribute="transaction" action="new">

    <table>
        <tr>
            <td> ID:</td>
                <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
            <td><sf:input path="id" type="Long" placeholder="1"/></td>
        </tr>
        <tr>
            <td>Descr:</td>
                <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
            <td><sf:textarea path="descr" type="text" placeholder="Note text here"/></td>
        </tr>
    </table>

    <input type="submit" VALUE="Save"/>

</sf:form>

</body>

</html>