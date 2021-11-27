<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="../include/validarSesion.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../include/headers.jsp" %>
	</head>
	<body>
	
		<div class="container" style="margin-top:15px;">
		
			<h2>Actualizaci&oacute;n de Registro</h2>
			
			<form action="../../persona" method="POST">
				<div class="form-group col-md-4">
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" value="${sessionScope.persona.nombre}" name="nombre" id="nombre" required />
				</div>
				<div class="form-group col-md-4">
					<label for="apellido">Apellido</label>
					<input type="text" class="form-control" value="${sessionScope.persona.apellido}" name="apellido" id="apellido" required />
				</div>
				<div class="form-group col-md-4">
					<label for="edad">Edad</label>
					<input type="text" class="form-control" value="${sessionScope.persona.edad}" name="edad" id="edad" required />
				</div>
				<div class="form-group col-md-4">
					<label for="usuario">Usuario</label>
					<input type="text" class="form-control" value="${sessionScope.persona.usuario}" name="usuario" id="usuario" required />
				</div>
				
				<input type="hidden" value="${sessionScope.persona.id}" name="id" id="id" />
				
				<input style="cursor:pointer;" type="submit" class="btn btn-primary" value="Actualizar" />
	  			<a href="../principal.jsp" class="btn btn-secondary">Atr&aacute;s</a>
			</form>
			
		</div>
	</body>
	
</html>