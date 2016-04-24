<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version="1.0" encoding="UTF-8" ?>
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
			<c:url var="authUrl" value="/j_spring_security_check" />
			<form:form class="form-horizontal" modelAttribute="loginuser" action="${authUrl}" method="POST" >
				<div class="form-group">
				<label for="email1"  class="col-sm-2 control-label">email:</label>
					<div class="col-sm-10">
				       	<form:input path="email" class="form-control" name="email" required ="required" id="email1" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Adres email jest niepoprawny" placeholder="email"/>
				       	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				       		<form:errors path="email" class="text-danger"/>
				       	</c:if>
			       	</div>
		       	</div>
		       	<div class="form-group">
			        <label for="password1" class="col-sm-2 control-label">hasło:</label>
			        <div class="col-sm-10">
			        	<form:password path="haslo" class="form-control" name="haslo" pattern=".{8,}" required="required" title="Hasło wymaga 8 znaków" id="password1" placeholder="hasło"/>
				        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				        	<form:errors path="haslo"  class="text-danger"/>
				        </c:if>
			        </div>
		        </div>
		        <div class="form-group">
		        	<div class="col-sm-offset-2 col-sm-10">
		        		<div class="checkbox">
		        			<label>
								<input type="checkbox" name="_spring_security_remember_me"/>Zapamiętaj mnie!
							</label>
						</div>
					</div>
		        </div>
		        <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary pull-right">Zaloguj się</button>
				    </div>
				</div>
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    </form:form>
	    </div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	    <script src="<c:url value="/resources/js/carouselscript.js"/>"></script>
	</body>
</html>