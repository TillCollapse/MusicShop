<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h3 style="text-align: center">TWÓJ KOSZYK</h3>
	<div id="koszyk">
    <h4>Oto twoje zakupy. Tutaj zrealizujesz swoje zamówienie</h4>
    <table>
        <tr>
            <th>NAZWA PRODUKTU</th>         <th>CENA</th>                     <th>ILOSC</th>        <th>SUMA</th>
        </tr>
		<c:forEach var="prod" items="${produkty}">
        <tr>
            <td>${prod.nazwa}</td>   <td>${prod.cena}</td>    <td>${prod.ilosc}</td>      
        </tr>
        </c:forEach>
        <tr>
            <td></td>                        <td></td>                          <td></td>           <td>${koszt}</td>
        </tr>
        
    </table>
    <form action="<c:url value="/finish" />">
    <input type="submit" value="ZAKONCZ i ZAPŁAĆ" id="buttonsign" onClick="alert('DZIĘKUJEMY ZA ZAKUPY W NASZYM SKLEPIE')" style="margin-left:230px; margin-bottom: 30px;">
    </form>
</div>
</div>