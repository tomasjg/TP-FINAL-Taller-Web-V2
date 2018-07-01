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
                    <li><a href="#">EXCLUSIONES</a></li>
                    <li><a href="#">PLANES</a></li>
                    <li><a class="btn active white" href="#">TU PLAN</a></li>
                </ul>
            </nav>
		</header>
		
		<div class = "main container">
				
			<div id="loginbox"  class="mainbox col-md-12 col-md-offset-1 col-sm-1 col-sm-offset-2">
			
			<h3>Plan escogido: </h3>
			
		<div id="table" class="mainbox col-md-12  col-sm-10 ">
			
			<h3> ${pacienteDTO.plan.nombre}</h3>
			<h4>Intensidad: ${pacienteDTO.plan.intensidad } ${pacienteDTO.plan.caloriasDiarias } Kcal. diarias</h4> 

        <table id="acrylic">
            <thead>
                <tr>
                    <th>Comida</th>
                    <th>Alimentos</th>      
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Desayuno</td>
                    <td>${pacienteDTO.plan.desayuno }</td>
                </tr>
                <tr>
                    <td>Media mañana</td>
                    <td>${pacienteDTO.plan.colacion1}</td>
                </tr>
                <tr>
                    <td>Almuerzo</td>
                    <td>${pacienteDTO.plan.almuerzo}</td>
                </tr>
                <tr>
                    <td>Merienda</td>
                    <td>${pacienteDTO.plan.merienda}</td>
                </tr>
                <tr>
                    <td>Media tarde</td>
                    <td>${pacienteDTO.plan.colacion2}</td>
                </tr>
                <tr>
                    <td>Cena</td>
                    <td>${pacienteDTO.plan.cena}</td>
                </tr>
            </tbody>
        </table>
		</div>

<%-- 			<span>Listado de comidas:<br> ${pacienteDTO.plan.listaComidasPorDia}</span>
			<br> --%>
			

			
			<div class="mainbox col-md-6 ">
			<h3>Tiempo estimado en cumplir objetivo</h3>
			<c:set var="pesoPGPorMes" value="${(caloriasPGPorDia*4)/1000}" />
			<c:set var="contador" value="${peso}" />  
			<div class="chartjs-wrapper">
					<canvas id="chartjs-0" class="chartjs" width="undefined" height="undefined"></canvas>
					<script>new Chart(document.getElementById("chartjs-0"),{"type":"line","data":
					{"labels":["",<c:forEach var = "i" begin = "0" end = "${diasObjetivo / 30}">
		        					 "Mes ${i + 1}",
				      				</c:forEach>],
				      		"datasets":
						[{"label":"Peso","data":[
								<c:set var="pesoPGPorMes" value="${(caloriasPGPorDia*4)/1000}" />
								<c:set var="contador" value="${peso}" />   
								<c:forEach var = "i" begin = "0" end = "${(diasObjetivo / 30)+1}">	        	 
								${contador}, 
								<c:if test="${peso < pesoIdeal}">
								<c:set var="contador" value="${contador + pesoPGPorMes}" /> 
								</c:if>
								
								<c:if test="${peso > pesoIdeal}">
								<c:set var="contador" value="${contador - pesoPGPorMes}" /> 
								</c:if> 
								
								</c:forEach>
							${pesoIdeal}],"fill":
							false,"borderColor":"rgb(75, 192, 192)","lineTension":
								0.1}]},"options":{}});
					</script>
			</div>
			</div>
			
						<div class="mainbox col-md-5 ">
				<h3>Datos del Paciente</h3>
				<br>
				<span>Peso: ${peso}</span>
				<br>
				<span data-toggle="tooltip" data-placement="top" title="La tasa metabólica basal (TMB) es el cálculo de las calorías mínimas que precisa una persona para realizar sus funciones orgánicas cada día.">
					TMB: ${tmb}
				</span>
				<br>
				<span>Peso ideal: ${pesoIdeal}</span>
				<br>
				<c:if test="${peso > pesoIdeal}">
				<span>Peso a perder: ${pesoAPerderOGanar}</span>
				<br>
				</c:if>
				<c:if test="${peso < pesoIdeal}">
				<span>Peso a Ganar: ${pesoAPerderOGanar}</span>
				<br>
				</c:if>
				<c:if test="${tmb > pacienteDTO.plan.caloriasDiarias}">
				<span>Calorías perdidas por día: ${caloriasPGPorDia}</span>
				<br>
				</c:if>
				<c:if test="${tmb < pacienteDTO.plan.caloriasDiarias}">
				<span>Calorías Ganadas por día: ${caloriasPGPorDia}</span>
				<br>
				</c:if>
				<span>Días objetivo: ${diasObjetivo}</span>
			</div>
			
			</div>
		
			</div>
		
			<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>