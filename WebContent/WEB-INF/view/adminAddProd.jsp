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
			<div class="panel panel-primary">
				<div class="panel-heading">Panel dodawania nowego produktu</div>							 
				<form:form class="form-horizontal" method="POST" action="?${_csrf.parameterName}=${_csrf.token}" modelAttribute="addproduct"  enctype="multipart/form-data">
					<div class="form-group">
						<label for="checkbox1" class="col-sm-4 control-label">Czy dodawany produkt jest albumem muzycznym?</label>
						<div class="checkbox-inline">
		      				<form:checkbox path="czyalbum" id="checkbox1" value="true"/>Tak
		    			</div>
	    			</div>
					<div class="form-group">
						<label for="name1"  class="col-sm-2 control-label">Nazwa produktu:</label>
						<div class="col-sm-10">
					       	<form:input path="nazwa" id="name1" class="form-control" placeholder="Podaj nazwę nowego produktu" />
			        		<form:errors path="nazwa" class="text-danger"/> 
				       	</div>
					</div>
					<div class="form-group">
						<label for="producer1"  class="col-sm-2 control-label">Producent:</label>
						<div class="col-sm-10">
					       	<form:input path="producent" id="producer1" class="form-control" placeholder="Podaj nazwę producenta"  />
			        		<form:errors path="producent" class="text-danger"/>
				       	</div>
					</div>
					<div class="form-group">
						<label for="category1" class="col-sm-2 control-label">Kategoria:</label>
						<div class="col-sm-10">
					       	<form:input path="kategoria" id="category1" class="form-control" placeholder="Podaj kategorię produktu" />
			        		<form:errors path="kategoria" class="text-danger" /> 
				       	</div>
					</div>
					<div class="album" style="display:none;">
						<div class="form-group">
							<label for="genre1" class="col-sm-2 control-label">Gatunek:</label>
							<div class="col-sm-10">
						       	<form:input path="gatunek" id="genre1" class="form-control" placeholder="Podaj gatunek muzyczny"  />
				        		<form:errors path="gatunek" class="text-danger" /> 
					       	</div>
						</div>
				        <div class="form-group">
							<label for="name2"  class="col-sm-2 control-label">Imie:</label>
							<div class="col-sm-10">
						       	<form:input path="autorImie" id="name2" class="form-control" placeholder="Podaj imię artysty"  />
				        		<form:errors path="autorImie" class="text-danger" />
					       	</div>
						</div>
						<div class="form-group">
							<label for="surname1"  class="col-sm-2 control-label">Nazwisko:</label>
							<div class="col-sm-10">
						       	<form:input path="autorNazwisko" id="surname1" class="form-control" placeholder="Podaj nazwisko artysty" />
				        		<form:errors path="autorNazwisko" class="text-danger" /> 
					       	</div>
						</div>
				       	<div class="form-group">
							<label for="pseudonym1"  class="col-sm-2 control-label">Wykonawca:</label>
							<div class="col-sm-10">
						       	<form:input path="autorPseudonim" id="pseudonym1" class="form-control" placeholder="Podaj wykonawce"  />
				        		<form:errors path="autorPseudonim" class="text-danger" /> 
					       	</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description1"  class="col-sm-2 control-label">Opis produktu:</label>
						<div class="col-sm-10">
					       	<form:textarea path="opis" id="description1" class="form-control" rows="5" cols="30" placeholder="Podaj opis produktu"  />
			        		<form:errors path="opis" class="text-danger" /> 
				       	</div>
					</div>
					<div class="form-group">
						<label for="price1"  class="col-sm-2 control-label">Cena:</label>
						<div class="col-sm-10">
					       	<form:input path="cena" id="price1" class="form-control" placeholder="Podaj cenę produktu"  />
			        		<form:errors path="cena" class="text-danger" />
				       	</div>
					</div>
					<div class="form-group">
						<label for="availability1"  class="col-sm-2 control-label">Ilość produktu:</label>
						<div class="col-sm-10">
					    	<form:input type="number" path="ilosc" id="availability1" class="form-control" placeholder="Podaj ilość dostępnego produktu" pattern="\d+(,\d{2})?" title="Podaj odpowiedni format" />
			        		<form:errors path="ilosc" class="text-danger" />
				       	</div>
					</div>
					<div class="form-group">
				       	<label for="inputFile" class="col-sm-2 control-label">Miniaturka:</label>
				       	<div class="col-sm-10">
					        <input type="FILE" name="image" id="inputFile" class="form-control" accept="image/*" />
					        <form:errors path="image" class="text-danger" />
				        </div> 
			        </div>
			        <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary pull-right">Dodaj produkt</button>
					    </div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form:form>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	    <script>
	        $('#checkbox1').click(function() {
	            $(".album").toggle(this.checked);
	        });
		</script>
	</body>
</html>