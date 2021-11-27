package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.DAO.PersonaDAO;
import com.example.demo.model.Persona;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistroServlet() 
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		getServletContext()
			.getRequestDispatcher("/content/persona/registro.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			//Capturamos parametros de nuevo registro
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			int edad = Integer.parseInt(request.getParameter("edad"));
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			
			//Creamos nueva Persona
			Persona persona = new Persona();
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setEdad(edad);
			persona.setUsuario(usuario);
			persona.setPassword(password);
			
			//Guardamos persona creada en BBDD
			PersonaDAO personaDAO = new PersonaDAO();
			boolean registro = personaDAO.insertarActualizar(persona);
			
			//Agregamos la respuesta del servidor al request
			if(registro) {
				request.setAttribute("exito", true);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			request.setAttribute("error", true);
			getServletContext().getRequestDispatcher("/content/persona/registro.jsp").forward(request, response);
		}
	}
}
