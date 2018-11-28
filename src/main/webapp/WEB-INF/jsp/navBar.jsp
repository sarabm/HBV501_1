<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div>

    <a href="/">Home</a> |
    <a href="/transaction/all">All transactions</a> |
    <a href="/transaction">New transaction</a> |
    <a href="/account/all">My accounts</a> |
    <a onclick="document.forms['logoutForm'].submit()">Logout</a> |
    <form id="logoutForm" method="POST" action="/logout"/>


</div>