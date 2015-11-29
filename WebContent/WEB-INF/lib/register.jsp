<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="loguj">
	<form method="post" id="regForm" action="<c:url value="/registration" />">
		<input type="text" placeholder="Imie" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" title="Tylko litery" id="name" name="name" ><!--  required ="required"-->
		<input type="text" placeholder="Nazwisko" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}" title="Tylko litery" name="surname" ><!--required = "required"-->
		<input type="email" placeholder="email" name="email1" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" ><!--required = "required"-->
		<input type="email" placeholder="Powtórz email" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="email2"> <!--required = "required"-->
		<input type="password" placeholder="hasło" pattern=".{8,}" name="password1" title='8 znaków' ><!--required = "required"-->
		<input type="password" placeholder="Powtórz hasło" pattern=".{8,}" name="password2" title='8 znaków'> <!--required = "required"-->
		<input type="text" placeholder="Miasto" name="miasto" pattern="^[A-ZĄĆÓĘŁŃŚŹŻa-ząćęłńóśźż]{2,}"> <!--required="required"-->
		<input type="text" placeholder="kod pocztowy" pattern="[\d]{2}-[\d]{3}" title="np. 39-305" name="kodpocztowy"> <!--required="required"-->
                <input type="text" placeholder="adres" name="adres"><!-- required="required"--><br>
		<div id="lower">
			<input type="submit" id="buttonsign" value="Zarejestruj się" name="signup">
		</div>	
	</form>
	<!--f-->
</div>	


	
	

	
	