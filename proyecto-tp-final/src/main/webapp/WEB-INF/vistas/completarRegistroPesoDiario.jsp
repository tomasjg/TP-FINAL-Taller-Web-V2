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
                 <ul class="container">
                    <li><a href="home">Inicio</a></li>
                    <li><a href="paciente">Elegir Plan Nutricional</a></li>
                    <li><a class="btn active white" href="registrarPesoDiario">Registrar Peso Diario</a></li>
                    <li><a href="progresoSeleccionarPaciente">Ver Progreso</a></li>
                </ul>
            </nav>
		</header>
		<div class = "main container">	
		<br><br>
			<span>Tu Registro de hoy se a guardado correctamente.</span>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>