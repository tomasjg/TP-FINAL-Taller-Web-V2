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
			
			<br>
			
			<form:form action="planes" method="POST" modelAttribute="pacienteDTO">
			
				<form:input type="hidden" path="paciente.peso" value="${paciente.peso}"/>
				<form:input type="hidden" path="paciente.altura" value="${paciente.altura}"/>
				<form:input type="hidden" path="paciente.sexo" value="${paciente.sexo}"/>
				<form:input type="hidden" path="paciente.edad" value="${paciente.edad}"/>
				<form:input type="hidden" path="paciente.ejercicio" value="${paciente.ejercicio}"/>
			
				Seleccione Intensidad:<br>
				<form:radiobutton path = "intensidad" value = "Normal" label = "Normal" /><br>
                <form:radiobutton path = "intensidad" value = "Intenso" label = "Intenso" /><br>
				<br>
				Excluir alimentos:<br>
				
				<form:checkbox path="alimentosExcluidos" value="Carne"/>Sin carnes <br>
				<form:checkbox path="alimentosExcluidos" value="Lacteos"/>Sin lácteos<br>
							
				<br>
				Enfermedades:<br>
				
				<form:checkbox path="enfermedadesPadecidas" value="Hipertension"/>Hipertensión<br>
				<form:checkbox path="enfermedadesPadecidas" value="Celiaquia"/>Celiaquía<br>
				
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