<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="content/include/headers.jsp" %>
    	<link rel="stylesheet" type="text/css" href="content/resource/css/style.css" />
	</head>
	<body>
		
		<c:if test="${ exito }">
			<div class="alert alert-success" role="alert">
				Registro Exitoso! Ahora puede ingresar...
			</div>
		</c:if>
		
		<%
			String resultado = (String)request.getAttribute("mensajeError");
			String mensaje = "";
			
			if (resultado != null) {
				mensaje = resultado;
			}
		%>
		
		<div class="container">
        	<div class="row text-center login-page">
        		
        		<h2>Ejemplo CRUD JSP/Servlets</h2>
        	
	   			<div class="col-md-12 login-form">
	      			<form action="login" method="post"> 			
	         			<div class="row">
		    				<div class="col-md-12 login-form-header">
		       					<p class="login-form-font-header">Inicio De Sesi&oacute;n</span><p>
								<span style="color:#f00;"><%=mensaje %></span>
		    				</div>
						</div>
						<div class="row">
		   					<div class="col-md-12 login-from-row">
		      					<input name="usuario" id="usuario" type="text" placeholder="Usuario" required/>
		   					</div>
						</div>
						<div class="row">
		   					<div class="col-md-12 login-from-row">
		      					<input name="password" id="password" type="password" placeholder="Contraseña" required/>
		   					</div>
						</div>
						<div class="row">
		   					<div class="col-md-12 login-from-row button">
		      					<button class="btn btn-info">Entrar</button>
		   					</div>
		   					<div class="col-md-12 login-from-row link">
		      					<a href="registro">Registrate</a>
		   					</div>
						</div>
	    			</form>
				</div>
     		</div>
  		</div>
	</body>
</html>