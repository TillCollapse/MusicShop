<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>MusicLand</title>
	    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	    <link href="<c:url value="/resources/css/bootstrap.custom.css"/>" rel="stylesheet">
		<script src="<c:url value="/resources/js/loadpage.js" />"></script>
	</head>

	<body>
		<div class="jumbotron">
			<div class="container text-center">
				<h1><a href="<c:url value="/"/>" style="text-decoration:none; color:inherit;">MusicLand</a></h1>      
			    <p>Everything connected with music</p>
			</div>
		</div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
			    <div class="navbar-header">
			    	<a class="navbar-brand" href="<c:url value="/"/>">MusicLand</a>
			    </div>
			    <ul class="nav navbar-nav">
					<li class="active"><a href="#" onClick=loadPage("content","<c:url value="/resources/template/zasady.html" />")>Regulamin</a></li>
					<li><a href="#">Kontakt</a></li>
<sec:authorize url="/adminAddProd">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Panel administratora <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/adminAddProd" />">Dodaj produkt</a></li>
							<li><a href="<c:url value="/changeAuthorities"/>">Zmień uprawnienia użytkowników</a></li>
							<li><a href="<c:url value="/registeredOrder"/>">Zrealizuj zamówienia</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
					</li>
</sec:authorize> 
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
<c:if test="${pageContext.request.userPrincipal.name != null}">
	    		<li><a href=""><span class="glyphicon glyphicon-user"></span> ${username}</a></li>
				<li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Wyloguj</a></li>
				<li><a href="#" onClick = loadPage("content","<c:url value="/basket"/>")><span class="glyphicon glyphicon-shopping-cart"></span> Twój koszyk</a></li>
</c:if>
<c:if test="${pageContext.request.userPrincipal.name == null}">
				<li><a href="<c:url value="/login/loginProcess"/>"><span class="glyphicon glyphicon-log-in"></span> Zaloguj</a></li>
				<li><a href="<c:url value="/register"/>"> Zarejestruj</a></li>
</c:if>	
		      	</ul>
		  	</div>
		</nav>
		<div class="container-fluid">  
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-3">
					<div class="panel-group">
						<div class="panel panel-default">
<c:set var="i" value="0"></c:set>
<c:forEach var="catname" items="${catnameList}">
							<div class="panel-heading">
								<h4 class="panel-title">
								  <a data-toggle="collapse" href="#collapse${i}">${catname.nazwa_kategorii}</a>
								</h4>
							</div>
							<div id="collapse${i}" class="panel-collapse collapse">
								<ul class="list-group">
	<c:forEach var="genre" items="${genreList}">
		<c:if test="${catname.nazwa_kategorii == genre.nazwa_kategorii}">
								  <li class="list-group-item"><a href="<c:url value="/products?genre=${genre.gatunekid}&cat=${genre.kategoriaid}"/>">${genre.nazwa_gatunku}</a></li>
		</c:if>
	</c:forEach>
								</ul> 
						  </div>
<c:set var="i" value="${i + 1}"/>						  
</c:forEach>

<c:set var="j" value="0"></c:set>
<c:forEach var="catname" items="${catnameList2}">
							<div class="panel-heading">
								<h4 class="panel-title">
								  <a data-toggle="collapse" href="#collapse2${j}">${catname.nazwa_kategorii}</a>
								</h4>
							</div>
							<div id="collapse2${j}" class="panel-collapse collapse">
								<ul class="list-group">
	<c:forEach var="produ" items="${genreList2}">
		<c:if test="${catname.nazwa_kategorii == produ.nazwa_kategorii}">
								  <li class="list-group-item"><a href="<c:url value="/products2?produ=${produ.producentid}&cat=${produ.kategoriaid}"/>">${produ.nazwa_producenta}</a></li>
		</c:if>
	</c:forEach>
								</ul> 
						  </div>
<c:set var="j" value="${j + 1}"/>						  
</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-lg-9 col-md-9 col-sm-9" id="content">
			        
				</div>
				
			</div>
		</div>
		<footer class="container-fluid text-center">
			<p>Online Store Copyright</p>  
			<form class="form-inline">Get deals:
				<input type="email" class="form-control" size="50" placeholder="Email Address">
				<button type="button" class="btn btn-danger">Sign Up</button>
			</form>
		</footer>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	    <script src="<c:url value="/resources/js/carouselscript.js"/>"></script>
	    <script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		<!-- csrt for log out-->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="POST" id="logoutForm">
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
	</body>
</html>
