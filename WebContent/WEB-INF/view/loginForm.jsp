<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value="/resources/css/forms.css" />" rel="stylesheet">
</head>
<body>
	<div id="tytul">
			<meta charset="utf-8">
			<h1>MUSIC LAND</h1>
	</div>
	<div id="loguj">
		<form:form method="post" modelAttribute="user" action="" >
	        <label for="email">email:</label>
	       <form:input path="email" name="email" required ="required" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" />
	        <label for="password">hasło:</label>
	        <form:password path="haslo" id="password" name="password" required ="required"/>
	        <div id="lower">
				<input type="checkbox"><label class="check" for="checkbox">Zapamiętaj mnie!</label>
				<input type="submit" id="buttonlogin" value="Zaloguj się" name="login">
	        </div>
	    </form:form>
	</div>
</body>
</html>	

    