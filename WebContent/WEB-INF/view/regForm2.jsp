<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<link href="<c:url value="/resources/css/forms.css" />" rel="stylesheet">
	</head>
	<body>
		<div id="tytul">
				<meta charset="utf-8" >
				<h1>MUSIC LAND</h1>
		</div>
		<div id="loguj">
			<form:form method="POST" action="" modelAttribute="user">
						<form:input path="imie" placeholder="Imie" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" title="Tylko litery" id="name" name="name" required="required" />
						<form:errors path="imie" class="errors" /> 
						<form:input path="nazwisko" placeholder="Nazwisko" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" title="Tylko litery" name="surname" required="required" />
						<form:errors path="nazwisko" class="errors" /> 
						<form:input path="email" placeholder="email" name="email1" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]+" required="required" />
						<form:errors path="email" class="errors" /> 
						<form:input path="emailconfirm" placeholder="Powtórz email" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]+" name="email2" required="required" />
						<form:errors path="emailconfirm" class="errors" />  
						<form:password path="haslo" placeholder="hasło" pattern=".{8,}" name="password1" title='8 znaków' required="required" />
						<form:errors path="haslo" class="errors" /> 
						<form:password path="hasloconfirm" placeholder="Powtórz hasło" pattern=".{8,}" name="password2" title='8 znaków' required="required"/>
						<form:errors path="hasloconfirm" class="errors" />  
						<form:input path="miasto" placeholder="Miasto" name="miasto" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" required="required"/>
						<form:errors path="miasto" class="errors"/>  
						<form:input path="kodpocztowy" placeholder="kod pocztowy" pattern="[\d]{2}-[\d]{3}" title="np. 39-305" name="kodpocztowy" required="required"/>
						<form:errors path="kodpocztowy" class="errors"/>  
				        <form:input path="adres" placeholder="adres" name="adres" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}(..)[0-9]{1,}[A-Za-z]{0,1}\\{0,1}[1-9]{0,}" required="required"/>
				        <form:errors path="adres" class="errors"/> 
						<div id="lower">
							<input type="submit" id="buttonsign" value="Zarejestruj się" name="signup" >
						</div>	
		        </form:form>
		</div>
	</body>	
</html>  
<script>
function validate() {
	var email = document.getElementById("email");
	var emailconfirm = document.getElementById("emailconfirm");
	var haslo = document.getElementById("haslo");
	var hasloconfirm = document.getElementById("hasloconfirm");
	if( (email === emailconfirm) && (haslo === hasloconfirm) ) {
		alert("Rejestracja nie powiodła się upewnij się, że emiale i hasla sa takie same");
		return false;
	}
}
</script>
	
	

	
	