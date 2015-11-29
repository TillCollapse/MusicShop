<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Panel admina</title>
		<style type="text/css">
		span.errors
		{
			color: red;
			display: block;
			width: 360px; 
			margin: 0px auto 15px auto;
		}
		</style>
	</head>
	<body>			
									 
		<form:form method="POST" action="" modelAttribute="addproduct"  enctype="multipart/form-data">
			<p>Czy dodawany produkt jest albumem muzycznym?</p>
			<form:checkbox path="czyalbum" value="true"/>Tak
			
			<p>Podaj nazwę produktu</p>
	        <form:input path="nazwa" />
	        <form:errors path="nazwa" class="errors" /> 
	        
	        <p>Podaj nazwę producenta</p>
	        <form:input path="producent" />
	        <form:errors path="producent" class="errors" /> 
	        
	        <p>Podaj kategorię</p>
	        <form:input path="kategoria" />
	        <form:errors path="kategoria" class="errors" /> 
	        
	        <p>Podaj gatunek</p>
	        <form:input path="gatunek" />
	        <form:errors path="gatunek" class="errors" /> 
	        
	        <p>Podan dane o autorze autorze</p>
	        <p>Imie</p>
	        <form:input path="autorImie" />
	        <form:errors path="autorImie" class="errors" />
	        <p>Nazwisko</p>
	        <form:input path="autorNazwisko" />
	        <form:errors path="autorNazwisko" class="errors" /> 
	        <p>Pseudonim artystyczny</p> 
	        <form:input path="autorPseudonim" />
	        <form:errors path="autorPseudonim" class="errors" /> 
	        
	        <p>Opis produktu</p>
	        <form:textarea path="opis" rows="5" cols="30" />
	        <form:errors path="opis" class="errors" /> 
	        
	        <p>Podaj cenne</p>
	        <form:input path="cenna" />
	        <form:errors path="cenna" class="errors" />
	        
	        <p>Podaj ilosc dostepnego produktu</p>
	        <form:input type="number" path="ilosc" />
	        <form:errors path="ilosc" class="errors" />
	         
	        <p>Wybierz miniaturkę dla produktu</p>  
	        <input type="FILE" name="image" />
	        <form:errors path="image" class="errors" /> 
	        
	        <input type="submit" name="dodajprodukt" value="Dodaj produkt" />
	        	
		</form:form>
	</body>
</html>