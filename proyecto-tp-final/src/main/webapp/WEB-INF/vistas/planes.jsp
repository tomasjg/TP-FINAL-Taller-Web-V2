<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<span>imc = ${imc}</span>
			<br>
			<span>Peso Ideal = ${pesoIdeal}</span>
			<br>
			<span>tmb = ${tmb}</span>
			<br>
			<span>altura = ${paciente.altura}</span>
			<br>
			<span>peso = ${paciente.peso}</span>
			<br>
			<span>edad = ${paciente.edad}</span>
			<br>
			<span>sexo = ${paciente.sexo}</span>
			<br>
			<span>ejercicio = ${paciente.ejercicio}</span>
			<br>
			<form:form action="planes" method="POST" modelAttribute="plan">
				Seleccione Plan:
				<form:select path="calorias">
					<c:forEach items="${planes}" var="lista_planes">		
							<c:if test="${lista_planes.calorias < tmb}">
							<form:option value="${lista_planes.calorias}">${lista_planes.nombre} - ${lista_planes.calorias} Calorias Diarias</form:option>
							</c:if>
					</c:forEach>
				</form:select>
				<br>
				<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Siguiente</button>
			</form:form>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
