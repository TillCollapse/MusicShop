<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<form:form  class="form-horizontal" method="POST" action="" modelAttribute="user">
				<div class="form-group">
					<label for="name1"  class="col-sm-2 control-label">Imię:</label>
						<div class="col-sm-10">
					       	<form:input path="imie" class="form-control" placeholder="Imie" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" title="Tylko litery" id="name1" name="name" required="required" />
					       	<form:errors path="imie" class="text-danger"/>
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="surname1"  class="col-sm-2 control-label">Nazwisko:</label>
						<div class="col-sm-10">
					       	<form:input path="nazwisko" class="form-control" placeholder="Nazwisko" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" title="Tylko litery" id="surname1" name="surname" required="required" />
							<form:errors path="nazwisko" class="text-danger" />
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="email1"  class="col-sm-2 control-label">Email:</label>
						<div class="col-sm-10">
					       	<form:input path="email" class="form-control" placeholder="email" name="email1" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]+" id="email1" required="required" />
							<form:errors path="email" class="text-danger" />
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="emailconfirm1"  class="col-sm-2 control-label">Powtórz email:</label>
						<div class="col-sm-10">
					       	<form:input path="emailconfirm" class="form-control"  placeholder="Powtórz email" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]+" id="emailconfirm1" name="email2" required="required" />
							<form:errors path="emailconfirm" class="text-danger" />
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="password1"  class="col-sm-2 control-label">Hasło:</label>
						<div class="col-sm-10">
					       	<form:password path="haslo" class="form-control" placeholder="hasło" pattern=".{8,}" id="password1" name="password1" title='8 znaków' required="required" />
							<form:errors path="haslo" class="text-danger" />
				       	</div>
			   	</div>
				<div class="form-group">
					<label for="passwordconfirm1"  class="col-sm-2 control-label">Powtórz hasło:</label>
						<div class="col-sm-10">
					       	<form:password path="hasloconfirm" class="form-control" placeholder="Powtórz hasło" pattern=".{8,}" id="passwordconfirm1" name="password1" title='8 znaków' required="required" />
							<form:errors path="hasloconfirm" class="text-danger" />
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="city1"  class="col-sm-2 control-label">Miejscowość:</label>
						<div class="col-sm-10">
					       	<form:input path="miasto" class="form-control" placeholder="Miasto" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" id="city1" name="miasto"  required="required"/>
							<form:errors path="miasto" class="text-danger"/> 
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="postcode1"  class="col-sm-2 control-label">Kod pocztowy:</label>
						<div class="col-sm-10">
					       	<form:input path="kodpocztowy" class="form-control" placeholder="kod pocztowy" pattern="[\d]{2}-[\d]{3}" id="postcode1" name="kodpocztowy" title="np. 39-305" required="required"/>
							<form:errors path="kodpocztowy" class="text-danger"/> 
				       	</div>
			   	</div>
			   	<div class="form-group">
					<label for="address1"  class="col-sm-2 control-label">Adres:</label>
						<div class="col-sm-10">
					       	<form:input path="adres" class="form-control" placeholder="adres" id="address1" name="adres" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}(..)[0-9]{1,}[A-Za-z]{0,1}\\\{0,1}[1-9]{0,}" required="required"/>
		        			<form:errors path="adres" class="text-danger"/> 
				       	</div>
			   	</div>	
					<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
						  <label><input type="checkbox" id="stipulation" value="true">Oświadczam, że zapoznałem się i akceptuję treść regulaminu Konta Użytkownika Portalu MusicLand.pl oraz wyrażam zgodę na przetwarzanie moich danych osobowych w celu świadczenia zamówionych usług przez MusicLand Spółka z o.o.</label>
						</div>
				    </div>
				</div>	
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
						<button type="submit" id="submitBtn" class="btn btn-primary pull-right">Zarejestruj się</button>
				    </div>
				</div>
		    </form:form>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	    <script>
	    $( document ).ready(function() {
	    	$('#submitBtn').attr('disabled', true);
	    	$("#stipulation").click(function(){
	    		if($("#stipulation").prop('checked')) {
		    		$('#submitBtn').removeAttr('disabled');
		    	} else {
		    		$('#submitBtn').attr('disabled', true);
		    	}
	    	});
	    });
	    </script>
	</body>
</html>