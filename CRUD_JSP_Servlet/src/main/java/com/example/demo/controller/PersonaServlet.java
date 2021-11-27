package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.DAO.PersonaDAO;
import com.example.demo.model.Persona;

@WebServlet("/persona")
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PersonaServlet() 
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			//Capturamos parametros
			String opcion = request.getParameter("opcion"); //Opcion seleccionada
			int id = Integer.parseInt( request.getParameter("id") ); //Id de persona
			HttpSession sesion = request.getSession(); //Sesion
			
			//Creamos objeto persona con el ID obtenido
			Persona persona = new Persona();
			persona.setId(id);
			
			//Creamos personaDAO para operar
			PersonaDAO personaDAO = new PersonaDAO();

			sesion.setAttribute( "persona", personaDAO.obtenerPorId(persona) );
			
			switch (opcion) {			
				case "ver" :
					response.sendRedirect("content/persona/ver.jsp");
					break;
				case "editar" :
					response.sendRedirect("content/persona/editar.jsp");
					break;
				case "eliminar" :
					//Eliminamos registro
					personaDAO.eliminar(persona);
					response.sendRedirect("content/principal.jsp");
					 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			//Capturamos parametros
			int id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			int edad = Integer.parseInt(request.getParameter("edad"));
			String usuario = request.getParameter("usuario");
			
			//Obtenemos a la persona segun su ID
			Persona persona = new Persona();
			persona.setId(id);
			
			PersonaDAO personaDAO = new PersonaDAO();
			persona = personaDAO.obtenerPorId(persona);
			
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setEdad(edad);
			persona.setUsuario(usuario);
			
			//Guardamos actualizacion en BBDD
			boolean registro = personaDAO.insertarActualizar(persona);
			
			//Agregamos la respuesta del servidor al request
			if(registro) {
				response.sendRedirect("content/principal.jsp");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
