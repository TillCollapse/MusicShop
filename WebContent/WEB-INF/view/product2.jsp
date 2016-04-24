<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version="1.0" encoding="UTF-8" ?>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
	<head>
		<title>STRONA GlOWNA</title>
		<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/menuleft.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/menutop.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/obrazki.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/table.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
		
		<script src="<c:url value="/resources/js/jquery-2.1.3.js" />"></script>
		<script src="<c:url value="/resources/js/loadpage.js" />"></script> 
		
	</head>

	<body>
		<div id="tytul">
			<h1>MUSIC LAND</h1>
		</div>
		<div id="log">
			<c:if test="${sessionid != null}">
				<a href="<c:url value="logedOutUser"/>">Wyloguj</a>
			</c:if>
			<c:if test="${sessionid == null}">
				<a href="#" onClick=loadPage("panel","<c:url value="/login"/>")>Zaloguj</a>
				<a href="#" onClick=loadPage("panel","<c:url value="/register"/>")>Rejestracja</a>
			</c:if>	
			
		</div>
                
		 <div id="panel">
		 	<c:if test="${sessionid != null}">
	            <div>
	           		<a href="#" onClick =loadPage("prawy","<c:url value="/basket"/>")><img src="<c:url value="resources/icons/trolley.png" />" id="koszykimg" /></a>
	            </div>
       		</c:if>       
			<div id="naglowek">
				<div id="menutop">
					<ul>
                                            <li><a href="<c:url value="/"/>">Home</a></li>
                                            <li><a href="#" onClick=loadPage("prawy","<c:url value="/resources/template/kontakt.html" />")>Kontakt</a></li>
                                            <li><a href="#" onClick=loadPage("prawy","<c:url value="/resources/template/zasady.html" />")>Zasady</a></li>

					</ul>
				</div>
			</div>

                     
			<div id="lewy">
                            
				<div id="menuleft">
					<label>Kategorie</label>
		                <ul>
			           		<c:forEach var="catname" items="${catnameList}">
				           		<li>${catname.nazwa_kategorii}
				           		
					                <ul>
					                
										<c:forEach var="genre" items="${genreList}">
											<c:if test="${catname.nazwa_kategorii == genre.nazwa_kategorii}">
						                		<li><a href="<c:url value="/products?genre=${genre.gatunekid}&cat=${genre.kategoriaid}"/>">${genre.nazwa_gatunku}</a> </li>
						                	</c:if>
						                </c:forEach>
					
					          		</ul>
			
							</c:forEach>
			               		</li>
			                
			             
		 <c:forEach var="catname" items="${catnameList2}">
				           		<li>${catname.nazwa_kategorii}
				           		
					                <ul>
					                
										<c:forEach var="produ" items="${genreList2}">
											<c:if test="${catname.nazwa_kategorii == produ.nazwa_kategorii}">
						                		<li><a href="<c:url value="/products2?produ=${produ.producentid}&cat=${produ.kategoriaid}"/>">${produ.nazwa_producenta}</a> </li>
						                	</c:if>
						                </c:forEach>
					
					          		</ul>
			
							</c:forEach>
			               		</li>
							
						</ul>
				</div>
			</div>
			<div id="prawy">
	            <div id="obraz">
					<img src="<c:url value="/resources/pictures/${product.zdjecie}"/>" height="250" width="250"/>
	                <div id="opis">
	                    <textarea maxlength="700" readonly>${product.opis}</textarea>
	                </div>
	                 <c:if test="${pageContext.request.userPrincipal.name != null}"> 
		                <form id="idForm>" action="" method="POST">
			                <input type="hidden" name="idprod" value="${product.produktid}">
			                <input type="number" pattern="[0-9]" name="iloscprod" class="ilosc" tittle="liczba sztuk produktu">
			                <input type="image" name="addForm" src="<c:url value ="resources/icons/plus.png"/>" alt="Submit Form" title="Dodaj do koszyka" height="65" width="65" class="imgClass" >
		                </form>
	                </c:if>
	
	            </div>        
       		</div>
			<div id="stopka"></div>
		</div>
	</body>
</html>