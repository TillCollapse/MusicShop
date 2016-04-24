<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>MusicLand</title>
	    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
		<script src="<c:url value="/resources/js/loadpage.js" />"></script>
	</head>
	<body>
		<div class="jumbotron">
			<div class="container text-center">
				<h1><a href="<c:url value="/"/>" style="text-decoration:none; color:inherit;">MusicLand</a></h1>      
			    <p>Everything connected with music</p>
			</div>
		</div>
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading">Panel realizacji zamówień</div>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>IMIĘ</th>
								<th>NAZWISKO</th>
								<th>EMAIL</th>
								<th>DATA</th>
								<th>STATUS ZAMÓWIENIA</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="i" value="0"></c:set>
							<c:forEach var="tmp" items="${userdatas}">
							<tr>
								<td>${i + 1}</td>
								<td>${tmp.imie}</td>
								<td>${tmp.nazwisko}</td>
								<td>${tmp.email}</td>
								<td>${tmp.data}</td>
								<td>${tmp.status}</td>
								<td><a href="<c:url value="registeredOrderDetails?id=${tmp.koszykid}"/>">realizuj zamówienie</a></td>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>	
	</body>
</html>