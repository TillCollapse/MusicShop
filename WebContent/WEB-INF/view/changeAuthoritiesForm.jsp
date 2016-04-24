<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version="1.0" encoding="UTF-8" ?>
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
				<div class="panel-heading">Panel zmiany uprawnień użytkowników</div>
				<div class="table-responsive">
					<table class="table table-striped"> 
						<thead>
							<tr>
								<th>#</th>
								<th>IMIĘ</th>
								<th>NAZWISKO</th>
								<th>EMAIL</th>
								<th>UPRAWNIENIA</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="i" value="0"></c:set>
						<c:forEach var="tmp" items="${userauthority}">
							<tr>
								<td>${i + 1}</td>
								<td>${tmp.imie}</td>
								<td>${tmp.nazwisko}</td>
								<td>${tmp.email}</td>
								<td>
									<form id="${i}" class="form-inline"method="POST" >
										<div class="form-group">
											<select class="form-control" name="authority" id="authority${i}">
												<option value="${tmp.authority}">${tmp.authority}</option>
												<c:forEach var="auth" items="${authorities}">
													<c:if test="${auth != tmp.authority}">
														<option value="${auth}">${auth}</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<input type="hidden" name="email" id="email${i}" value="${tmp.email}" />
										<button type="submit" class="submitForm btn btn-primary form-control" data-toggle="tooltip" value="Save Changes">Zmień</button>
									</form>
								</td>
								<td style="min-width: 190px;">
									<span id="result${i}" class="text-success"></span>
								</td>
							</tr>
							<c:set var="i" value="${i + 1}"></c:set>
						</c:forEach>	
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
		<script type="text/javascript">
			$(".submitForm").click(function(){
				var id = $(this.form).attr('id'); 
				$.ajax({
				  mimeType:"text/html; charset=UTF-8",
				  type: "POST",
				  url: "",
				  cache: false,    
				  data: $(this).parent().serialize(),
				  success: function(response) {
					  $("#result" + id).text(response).show();
					  $("#result" + id).fadeOut(4000);
				  },
				  error: function(){      
				   alert('Nie udało się zmienić uprawnień użytkownika');
				  }
				 });
				return false;
			});
		</script>
	</body>
</html>