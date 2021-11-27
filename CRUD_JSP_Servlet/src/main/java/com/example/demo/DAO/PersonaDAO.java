package com.example.demo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Conexion;
import com.example.demo.model.Persona;

//Clase implementadora de metodos CRUD
public class PersonaDAO 
{
	private Connection connection;
	private Conexion conexion;
	
	//Obtenemos conexion con el constructor
	public PersonaDAO()
	{
		conexion = new Conexion("jdbc:mysql://localhost:3306/database_pruebas", "root", "");
	}
	
	//INSERTAR - ACTUALIZAR
	public boolean insertarActualizar(Persona persona) throws SQLException
	{
		String sql = "";
		
		//Creamos sentencia SQL evaluando si es INSERTAR o ACTUALIZAR
		if( persona.getId() > 0 ) {
			sql = "UPDATE persona SET nombre = ?, apellido = ?, edad = ?, usuario = ? WHERE id = ?";
		}else {
			sql = "INSERT INTO persona(nombre, apellido, edad, usuario, password) VALUES(?,?,?,?,?)";	
		}
		
		//Obtenemos la conexion
		conexion.conectar();
		connection = conexion.getConnection();
		
		//Preparamos la consutla para su ejecucion
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, persona.getNombre());
		preparedStatement.setString(2, persona.getApellido());
		preparedStatement.setInt(3, persona.getEdad());
		preparedStatement.setString(4, persona.getUsuario());
		
		//Evaluamos si estamos insertando o actualizando
		if( persona.getId() > 0 ) {
			preparedStatement.setInt(5, persona.getId());
		}else {
			preparedStatement.setString(5, persona.getPassword());
		}
		
		//Ejecutamos consulta y guardamos resultado
		boolean respuestaConsulta = preparedStatement.executeUpdate() > 0;
		
		//Cerramos conexiones
		preparedStatement.close();
		conexion.desconectar();
		
		return respuestaConsulta;
	}
	
	//LISTAR
	public List<Persona> listar() throws SQLException
	{
		//Creamos lista de personas y sentencia SQL
		List<Persona> listaPersonas = new ArrayList<Persona>();
		String sql = "SELECT * FROM persona";
		
		//Obtenemos la conexion
		conexion.conectar();
		connection = conexion.getConnection();
		
		//Crreamos Statement y ejecutamos consulta SQL
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		//Recorremos la lista de resultados obtenidos de la consulta
		while( resultSet.next() ) {
			
			//Creamos objeto persona y asignamos parametros obtenidos
			Persona persona = new Persona();
			
			persona.setId(resultSet.getInt("id"));
			persona.setNombre(resultSet.getString("nombre"));
			persona.setApellido(resultSet.getString("apellido"));
			persona.setEdad(resultSet.getInt("edad"));
			persona.setUsuario(resultSet.getString("usuario"));
			persona.setPassword(resultSet.getString("password"));
			
			listaPersonas.add( persona );
		}
		
		return listaPersonas;
	}
	
	//OBTENER POR ID
	public Persona obtenerPorId(Persona persona) throws SQLException
	{
		//Creamos sentencia SQL
		String sql = "SELECT * FROM persona WHERE id = ?";
		
		//Obtenemos conexion
		conexion.conectar();
		connection = conexion.getConnection();
		
		//Preparamos consulta 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, persona.getId());
		
		//Ejecutamos consulta SQL
		ResultSet resultSet = preparedStatement.executeQuery();
		
		//Creamos objeto persona para guardar resultado
		Persona person = new Persona();
		
		//Recorremos los resultados
		while(resultSet.next()) {
			
			//Almacenamos el resultado en obtejo person
			person.setId(resultSet.getInt("id"));
			person.setNombre(resultSet.getString("nombre"));
			person.setApellido(resultSet.getString("apellido"));
			person.setEdad(resultSet.getInt("edad"));
			person.setUsuario(resultSet.getString("usuario"));
			person.setPassword(resultSet.getString("password"));
		}	
		
		return person;
	}
	
	//OBTENER POR USUARIO Y PASSWORD
	public Persona obtenerPorUsuarioPassword(Persona persona) throws SQLException
	{
		//Creamos sentencia SQL
		String sql = "SELECT * FROM persona WHERE usuario = ? AND password = ?";
		
		//Obtenemos conexion
		conexion.conectar();
		connection = conexion.getConnection();
		
		//Preparamos consulta 
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, persona.getUsuario());
		preparedStatement.setString(2, persona.getPassword());
		
		//Ejecutamos consulta SQL
		ResultSet resultSet = preparedStatement.executeQuery();
		
		//Creamos objeto persona para guardar resultado
		Persona person = new Persona();
		
		//Recorremos los resultados
		while(resultSet.next()) {
			
			//Almacenamos el resultado en obtejo person
			person.setId(resultSet.getInt("id"));
			person.setNombre(resultSet.getString("nombre"));
			person.setApellido(resultSet.getString("nombre"));
			person.setEdad(resultSet.getInt("edad"));
			person.setUsuario(resultSet.getString("usuario"));
			person.setPassword(resultSet.getString("password"));
		}	
		
		return person;
	}
	
	//ELIMINAR
	public boolean eliminar(Persona persona) throws SQLException
	{
		String sql = "DELETE FROM persona WHERE id = ?";
		
		conexion.conectar();
		connection = conexion.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, persona.getId());
		
		boolean resultadoConsulta = preparedStatement.executeUpdate() > 0;
		
		return resultadoConsulta;
	}
}
