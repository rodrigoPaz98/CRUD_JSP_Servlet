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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() 
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			//Creamos objeto Persona y asignamos usuario y contraseña capturados
			Persona persona = new Persona();
			persona.setUsuario( request.getParameter("usuario") );
			persona.setPassword( request.getParameter("password") );
			
			//Obtenemos objeto Persona segun usuario y password
			PersonaDAO personaDAO = new PersonaDAO();
			
			persona = personaDAO.obtenerPorUsuarioPassword(persona);
			
			if(persona.getId() > 0) {
				
				//Abrimos sesion
				HttpSession session = request.getSession();
				session.setAttribute("usuario", persona);
				
				//Redireccionamos
				response.sendRedirect("content/principal.jsp");
				
			}else {
				
				//Mostramos mensaje de error
				request.setAttribute("mensajeError", "Usuario o Contraseña Incorrecta");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
