<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li><a  href="home">Inicio</a></li>
                    <li><a href="paciente">Elegir Plan Nutricional</a></li>
                    <li><a href="registrarPesoDiario">Registrar Peso Diario</a></li>
                    <li><a class="btn active white" href="#">Ver Progreso</a></li>
                </ul>
            </nav>
		</header>
		<div class = "main container">
		
		<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">Fecha</th>
			      <th scope="col">Peso Ideal</th>
			      <th scope="col">Peso Registrado por Usuario</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${Lista}" var="listado">
			    <tr>
			      <td>${listado.fecha}</td>
			      <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${listado.pesoIdeal}"/></td>
			      <td>${listado.pesoRegistrado}</td>
			    </tr>
			  </c:forEach> 
			  </tbody>
		</table>
			

			
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>