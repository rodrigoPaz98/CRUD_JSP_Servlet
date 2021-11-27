package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase para conexion con BBDD
public class Conexion 
{
	private Connection connection;
	private String url;
	private String username;
	private String password;
	
	//Inicializamos parametros al crear instancia de la clase
	public Conexion(String url, String username, String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	//Metodo para crear una conexion sencilla a una BBDD
	public void conectar() throws SQLException
	{
		if( connection == null || connection.isClosed() ) {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Metodo para desconectar de la BBDD
	public void desconectar() throws SQLException
	{
		if( connection != null && !connection.isClosed() ) {
			connection.close();
		}
	}
	
	//Metodo para retornar la conexion establecida
	public Connection getConnection() 
	{
		return connection;
	}
}
