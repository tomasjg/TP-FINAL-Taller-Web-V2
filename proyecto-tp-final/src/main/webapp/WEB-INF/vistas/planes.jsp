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
			
			<h3>Estos son los planes sugeridos: </h3>
			
			<c:if test="${pacienteDTO.excluirCarne}" >
				<span>Sin carnes</span>
			</c:if>
			<c:if test="${pacienteDTO.excluirLacteos}" >
				<span>Sin lácteos</span>
			</c:if>
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
			<form:form action="final" method="POST" modelAttribute="pacienteDTO">
			
			  
				<c:forEach items="${planesSugeridos}" var = "item" >
					<form:radiobutton path="plan.id" value="${item.id}"/> ${item.nombre} - ${item.caloriasDiarias} calorías diarias <br>
				</c:forEach>
			
				<br><b>Plan Recomendado:</b>
				<!--
				<form:radiobutton path="plan.id" value="${planSugerido.id}" checked="checked"/> ${planSugerido.nombre} - ${planSugerido.caloriasDiarias} calorías diarias <br>
				-->  
				${planSugerido.nombre} - ${planSugerido.caloriasDiarias} calorías diarias <br>
				
				<br>
				
				<form:input type="hidden" path="paciente.peso" value="${paciente.peso}"/>
				<form:input type="hidden" path="paciente.altura" value="${paciente.altura}"/>
				<form:input type="hidden" path="paciente.sexo" value="${paciente.sexo}"/>
				<form:input type="hidden" path="paciente.edad" value="${paciente.edad}"/>
				<form:input type="hidden" path="paciente.ejercicio" value="${paciente.ejercicio}"/>
				
				<br>
				<button class="btn btn-lg btn-primary btn-block" Type="Submit">Ver Plan</button>
	  	    </form:form>
	  	    
	  	    </div>
	  	   </div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
