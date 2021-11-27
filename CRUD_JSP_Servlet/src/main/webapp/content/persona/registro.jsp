<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../include/headers.jsp" %>
	</head>
	<body>
		
		<c:if test="${ error }">
			<div class="alert alert-warning" role="alert">
				Error! Hubo un problema al procesar los datos, intente mas tarde...
			</div>
		</c:if>
		
		<div class="container" style="margin-top:15px;">
		
			<h2>Registro</h2>
			
			<form action="registro" method="POST">
				<div class="form-group col-md-4">
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" required>
				</div>
				<div class="form-group col-md-4">
					<label for="apellido">Apellido</label>
					<input type="text" class="form-control" name="apellido" id="apellido" placeholder="Apellido" required>
				</div>
				<div class="form-group col-md-4">
					<label for="edad">Edad</label>
					<input type="number" class="form-control" min="0" max="100" name="edad" id="edad" placeholder="Edad" required>
				</div>
				<div class="form-group col-md-4">
					<label for="usuario">Usuario</label>
					<input type="text" class="form-control" name="usuario" id="usuario" placeholder="Usuario" required>
				</div>
				<div class="form-group col-md-4">
					<label for="apellido">Contrase&ntilde;a</label>
					<input type="password" class="form-control" name="password" id="password" placeholder="Contraseña" required>
				</div>
	  			
	  			<button style="cursor:pointer;" type="submit" class="btn btn-primary">Registrar</button>
	  			<a href="login" class="btn btn-secondary">Cancelar</a>
			</form>
			
		</div>
	</body>
	
</html>