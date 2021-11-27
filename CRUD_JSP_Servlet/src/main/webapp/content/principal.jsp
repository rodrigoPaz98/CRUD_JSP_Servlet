<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.demo.model.Persona, com.example.demo.DAO.PersonaDAO, java.util.List"%>
    
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="include/validarSesion.jsp" %>

<%
	//Obtenemos todos los registros
	PersonaDAO personaDAO = new PersonaDAO();
	List<Persona> listaPersonas = personaDAO.listar();
	
%>

<!DOCTYPE html>
<html>
	<head>    	
    	<%@ include file="include/headers.jsp" %>
	</head>
	<body>
		<div class="container" style="margin-top:30px;">
			
			<h2>Lista Personas</h2>
			
			<form style="float:right;margin-top:-35px;margin-bottom:35px;" action="../login" method="GET">
				<input style="cursor:pointer;" type="submit" class="btn btn-primary" value="Cerrar Sesi&oacute;n" />
			</form>
			
			<table class="table">
	  			<thead>
	    			<tr>
	      				<th scope="col">ID</th>
	      				<th scope="col">NOMBRE</th>
	      				<th scope="col">APELLIDO</th>
	      				<th scope="col">ACCION</th>
	    			</tr>
	  			</thead>
	  			<tbody>
	  				<c:forEach items="<%= listaPersonas %>" var="persona">
	  				
	    				<tr>
	      					<th scope="row">${persona.id}</th>
	      					<td>${persona.nombre}</td>
	      					<td>${persona.apellido}</td>
	      					<td>
	      						<a href="../persona?opcion=ver&id=${persona.id}" style="color:#000;margin-right:5px;"><span class="fas fa-eye"></span></a>
	      						<a href="../persona?opcion=editar&id=${persona.id}" style="color:#000;margin-right:5px;"><span class="fas fa-pencil-alt"></span></a>
	      						<a 
	      							href="../persona?opcion=eliminar&id=${persona.id}" 
	      							style="color:#000;margin-right:5px;"
	      							onclick=" return confirm('¿Seguro Desea Eliminar este Registro?') ">
	      							
	      							<span class="fas fa-eraser"></span>
	      						</a> 
	      					</td>
	    				</tr>
	    				
	    			</c:forEach>
	  			</tbody>
			</table>
		</div>
	</body>
</html>