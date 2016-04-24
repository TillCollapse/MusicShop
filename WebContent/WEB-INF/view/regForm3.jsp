<%@ page contentType="text/html; charset=UTF-8" %>
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
						<form:input path="imie" placeholder="Imie"/>
						<form:errors path="imie" class="errors" /> 
						<form:input path="nazwisko" placeholder="Nazwisko"/>
						<form:errors path="nazwisko" class="errors" /> 
						<form:input path="email" placeholder="email" />
						<form:errors path="email" class="errors" /> 
						<form:input path="emailconfirm" placeholder="Powtórz email"/>
						<form:errors path="emailconfirm" class="errors" />  
						<form:password path="haslo" placeholder="hasło"/>
						<form:errors path="haslo" class="errors" /> 
						<form:password path="hasloconfirm" placeholder="Powtórz hasło"/>
						<form:errors path="hasloconfirm" class="errors" />  
						<form:input path="miasto" placeholder="Miasto"/>
						<form:errors path="miasto" class="errors"/>  
						<form:input path="kodpocztowy" placeholder="kod pocztowy"/>
						<form:errors path="kodpocztowy" class="errors"/>  
				        <form:input path="adres" placeholder="adres" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}(..)[1-9]{1,}[A-Za-z]{0,1}\\\{0,1}[1-9]{0,}"/>
				        <form:errors path="adres" class="errors"/> 
						<div id="lower">
							<input type="submit" id="buttonsign" value="Zarejestruj się" name="signup" >
						</div>	
		        </form:form>
		</div>
	</body>	
</html>  

	
	

	
	