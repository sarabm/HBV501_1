<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">

    <a href="/">Home</a> |
    <a href="/transaction">All transactions</a> |
    <a href="/transaction/new">New transaction</a> |
    <u><h4 style="color: blue;">
    <a onclick="document.forms['logoutForm'].submit()">Logout</a>
    </h4></u>

    <form id="logoutForm" method="POST" action="/logout">
    </form>


</div>