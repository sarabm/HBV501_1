<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../navBar.jsp" />

<html lang="en">

    <head>
        <title>Lil Bill</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/account/payUp.css"/>"/>
    </head>
    <body>
    <div class="transaction-list-container">
        <div class="transaction-list-inner">

            <div class="new-transaction-container">
                <div class=new-transaction>
                    <h1>Pay Up</h1>

                    <%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
                    <%--that is added to the model that is passed to the view.--%>
                    <%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
                    <sf:form method="POST" modelAttribute="transaction" action="/payup">
                        <div class="pay-up-form-group ">
                            <h2>Payment to ${friend}</h2>
                        <div class="form-amount">
                            ${-transaction.amount}
                        </div>
                            <div>
                                <input class="button" type="submit" VALUE="Pay Up" onclick="form.action='payup';"/>
                            </div>

                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>

    </body>

    <footer></footer>
</html>
