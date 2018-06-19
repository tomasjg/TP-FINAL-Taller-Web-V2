<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="css/estilos.css">
	    <script src="<c:url value="js/Chart.min.js" />"></script>
	</head>
	<body>
		
		<header class="header container">
			<h1 class="logo">Control Nutricional</h1>
            <nav>
                 <ul class="">
                    <li><a href="#">PACIENTE</a></li>
                    <li><a href="#">EXCLUCIONES</a></li>
                    <li><a href="#">PLANES</a></li>
                    <li><a class="btn active white" href="#">TU PLAN</a></li>
                </ul>
            </nav>
		</header>
		
		<div class = "main container">
				
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			
			<h3>Plan escogido: </h3>
			
			<span>ID: ${pacienteDTO.plan.id}</span>
			<br>
			<span>Nombre: ${pacienteDTO.plan.nombre}</span>
			<br>
			<span>Calorías diarias: ${pacienteDTO.plan.caloriasDiarias}</span>
			<br>
			<span>Intensidad: ${pacienteDTO.plan.intensidad}</span>
			<br>
			<span>Listado de comidas: ${pacienteDTO.plan.listaComidasPorDia}</span>
			<br>
			<br>
			-----
			<br>
			<span>Peso: ${peso}</span>
			<br>
			<span>TMB: ${tmb}</span>
			<br>
			<span>Peso ideal: ${pesoIdeal}</span>
			<br>
			<span>Peso a perder: ${pesoAPerder}</span>
			<br>
			<span>Calorías perdidas por día: ${caloriasPerdidasPorDia}</span>
			<br>
			<span>Días objetivo: ${diasObjetivo}</span>
			<br>
			-----
			<c:set var="pesoPerdidoPorMes" value="${(caloriasPerdidasPorDia*4)/1000}" />
			<c:set var="contador" value="${peso}" />  
			<div class="chartjs-wrapper">
					<canvas id="chartjs-0" class="chartjs" width="undefined" height="undefined"></canvas>
					<script>new Chart(document.getElementById("chartjs-0"),{"type":"line","data":
					{"labels":["",<c:forEach var = "i" begin = "0" end = "${diasObjetivo / 30}">
		        					 "Mes ${i + 1}",
				      				</c:forEach>],
				      		"datasets":
						[{"label":"Peso","data":[
							<c:set var="pesoPerdidoPorMes" value="${(caloriasPerdidasPorDia*4)/1000}" />
								<c:set var="contador" value="${peso}" />   
								<c:forEach var = "i" begin = "0" end = "${(diasObjetivo / 30)+1}">	        	 
								${contador}, 
								  	 <c:set var="contador" value="${contador - pesoPerdidoPorMes}" />  
								</c:forEach>
							${pesoIdeal}],"fill":
							false,"borderColor":"rgb(75, 192, 192)","lineTension":
								0.1}]},"options":{}});
					</script>
			</div>
			<br>
			</div>
		
			</div>
		
			<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>