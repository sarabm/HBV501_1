<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/navBar.css"/>"/>

<div class="navbar-container">
    <ul class="navbar">
        <div class="navbar--item navbar__left">
            <li >
                <a href="/">Lil Bill</a>
            </li>
        </div>
        <div class="navbar--item navbar__right">
            <li>
                <a href="/transaction">New Transaction</a>
            </li>
            <li>
                <a href="/accounts">Accounts</a>
            </li>
            <li class="button">
                <a onclick="document.forms['logoutForm'].submit()">Logout</a>
                <form id="logoutForm" method="POST" action="/logout"/>
            </li>

        </div>
    </ul>
</div>