<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="css/estilos.css">
	</head>
	<body>
		
		<header class="header container">
			<h1 class="logo">Control Nutricional</h1>
            <nav>
                 <ul class="">
                    <li><a href="#">PACIENTE</a></li>
                    <li><a href="#">EXCLUSIONES</a></li>
                    <li><a class="btn active white" href="#">PLANES</a></li>
                    <li><a href="#">TU PLAN</a></li>
                </ul>
            </nav>
		</header>
		
		<div class = "main container">
		
		<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			
			<h2>Plan seleccionado: </h2>
			
			<h3> ${plan.nombre}</h3> 
			<p>Intensidad: ${plan.intensidad}</p>
			<p>${plan.caloriasDiarias} KiloCalorias diarias</p>

			<span>${plan.listaComidasPorDia}</span>
		
			<c:if test="${pacienteDTO.aptoHipertenso}" >
				<span>Apto Hipertensión</span>
			</c:if>
			<c:if test="${pacienteDTO.aptoCeliaco}" >
				<span>Apto celiaquía</span>
			</c:if>
			<c:if test="${pacienteDTO.intensidad}" >
				<span>${pacienteDTO.intensidad}</span>
			</c:if>
			<span></span>
			<br>
			<br>
			
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