<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel panel-primary">
	<div class="panel-heading">Twój koszyk</div>
	<div class="panel-body">
    <p>Oto lista twoich zakupów. Tutaj zrealizujesz swoje zamówienie.</p>
  	</div>
  	<div class="table-responsive">
	    <table class="table table-striped">
	    	<thead>
		        <tr>
		        	<th>#</th>
		            <th>NAZWA PRODUKTU</th>         
		            <th>CENA</th>                     
		            <th>ILOSC</th>        
		            <th>SUMA</th>
		            <th></th>
		        </tr>
	        </thead>
	        <tbody>
	        	<c:set var="i" value="1"></c:set>
				<c:forEach var="prod" items="${produkty}">
		        <tr>
		        	<td>${i}</td>
		            <td>${prod.nazwa}</td>   
		            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${prod.cena}" />zł</td>    
		            <td>${prod.ilosc}</td>
		            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${prod.cena * prod.ilosc}" />zł</td>
		            <td><button type="button"data-id="${prod.produktid}" class="deleteBtn btn btn-primary form-control">Usuń z listy</button></td>    
		        </tr>
		        <c:set var="i" value="${i + 1}"></c:set>
		        </c:forEach>
		        <tr>
		            <td></td>                        
		            <td></td>                          
		            <td></td>
		            <td></td>            
		            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${koszt}" />zł</td>
		            <td></td>
		        </tr>
		        <tr>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td></td>
		        	<td><button type="button" id="cancelBtn" class="btn btn-primary form-control">Anuluj</button></td>
			        <td>
				        <form action="<c:url value="/finish" />">
					    	<button type="submit" class="submitForm btn btn-primary form-control" onClick="alert('DZIĘKUJEMY ZA ZAKUPY W NASZYM SKLEPIE')">Zamów</button>
					    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					    </form>
				    </td>
		        </tr>
	        </tbody>
	    </table>
	</div>
</div>
<script>
$( "#cancelBtn" ).click(function() {
	$.ajax({
		  mimeType:"text/html; charset=UTF-8",
		  url: "<c:url value="/cancel" />",  
		  data: null,
		  success: function(response) {
			  alert(response);
			  loadPage("content","<c:url value="/basket"/>");
		  },
		 });
	});
	$( ".deleteBtn" ).click(function() {
		var id = $(this).data("id");
		$.ajax({
			  mimeType:"text/html; charset=UTF-8",
			  url: "<c:url value="/delete?id="/>" + id,  
			  data: null,
			  success: function(response) {
				  loadPage("content","<c:url value="/basket"/>");
			  },
			 });
		});
</script>
