<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
			<div class="panel-heading">Dane użytkownika</div>
			<div class="table-responsive">
				<table class="table"> 
					<thead>
						<tr>
							<th>IMIĘ</th>
							<th>NAZWISKO</th>
							<th>EMAIL</th>
							<th>ADRES</th>
							<th>MIASTO</th>
							<th>KOD POCZTOWY</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${user.imie}</td>
							<td>${user.nazwisko}</td>
							<td>${user.email}</td>
							<td>${user.adres}</td>
							<td>${user.miasto}</td>
							<td>${user.kodpocztowy}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Szczegóły zamówienia</div>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>NAZWA PRODUKTU</th>
							<th>CENA zł/szt</th>
							<th>ILOSC</th>
							<th>KOSZT</th>
							<th>KOSZT CAŁKOWITY</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:set var="i" value="0"></c:set>
						<c:forEach var="tmp" items="${productslist}">
						<tr>
							<td>${i + 1}</td>
							<td>${tmp.nazwa}</td>
							<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${tmp.cena}"/>zł</td>
							<td>${tmp.ilosc}</td>
							<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${tmp.cena * tmp.ilosc}"/>zł</td>
							<td></td>
							<td style="min-width:50px;"><span class="change glyphicon glyphicon-remove"></span></td>
						</tr>
						<c:set var="i" value="${i + 1}"></c:set>
						</c:forEach>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${cost}"/>zł</td>
							<td>
								<form action="">
							    	<button type="submit" id="submitBtn" data-id="${koszykid}"class="btn btn-primary">Do wysyłki</button>
							    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						    	</form>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script>
	    function check(){
				elements = document.getElementsByClassName("change");
				var flag = 0;
				for (var i = 0; i < elements.length; i++){
					console.log(elements[i].style.color);
					if(elements[i].style.color == "green"){
						flag++;
					}
				}
				if(elements.length == flag) {
					$('#submitBtn').removeAttr('disabled');
				} else {
					$('#submitBtn').attr('disabled', true);
				}
			}
	    $( document ).ready(function() {
	    	$('#submitBtn').attr('disabled', true);
	    	$(".change").click(function() {
	    		if( $( this ).css("color") != "rgb(0, 128, 0)") {
	    			$( this ).toggleClass('glyphicon-remove glyphicon-ok').css("color", "green");
	    		} else  {
	    			$( this ).toggleClass('glyphicon-remove glyphicon-ok').css("color", "red");
	    		}
	    		check();
	    	});
	    	$( "#submitBtn" ).click(function() {
	    		var id = $(this).data("id");
	    		console.log("1234");
		    	$.ajax({
		    		  mimeType:"text/html; charset=UTF-8",
		    		  type: "GET",
		    		  url: "<c:url value="/tosend?id="/>" + id,
		    		  data: null,
		    		  success: function(response) {
		    			  alert(response);
		    			  window.location = "http://localhost:8090/MusicLand/registeredOrder";
		    		  },
		    		 });
		    	return false;
		    });
	    });
    </script>
    		
	</body>
</html>