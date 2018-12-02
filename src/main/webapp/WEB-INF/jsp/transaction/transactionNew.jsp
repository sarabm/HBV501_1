<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../navBar.jsp" />

<html lang="en">

<head>
    <title>Lil Bill</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/transaction/transactionNew.css"/>"/>
</head>
<body>

<div class="new-transaction-container">
    <div class=new-transaction>
<h1>Create a new transaction</h1>

<%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
<%--that is added to the model that is passed to the view.--%>
<%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
        <c:if test="${not empty msg}">
            <p>${msg}</p>
        </c:if>
<sf:form method="POST" modelAttribute="transaction" action="/transaction">

        <div class="form-group ">
            <sf:input class="form-input"  path="amount" type="number" placeholder="Enter amount" required=""/>
            <sf:textarea class="form-input" path="descr" type="text" placeholder="Write a description of the transaction"/>
            <table>
                <tr>
            <td>Split between</td>
            <td class="friendlist">
            <sf:checkboxes items = "${splitList}" path = "splitInfo" />
            </td>

            <%-- <td class="friendlist">
                <label for=Me>
                    <input type="checkbox" name="selected" id="Me" value="Me">
                    <span>Me</span>
                </label>
-               <c:forEach var="user" items="${friendlist}">
                    <label for="${user.username}">
                        <input type="checkbox" name="selected" id="${user.username}" value="${user.username}">
                        <span>${user.username}</span>
                    </label>
                </c:forEach>
            </td> --%>
        </tr>
    </table>

    <input class="button" type="submit" VALUE="Create Transaction" onclick="form.action='transaction';"/>
        </div>

</sf:form>

    </div>
    <div class="find-friends">
        <h2>Can't see your friends?</h2>
        <a class="button" href="/account/all">Add new friends</a>
    </div>
</div>
</body>

</html>