package com.ipartek.formacion.oscarfonticoba.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Clase que permite conectar con la base de datos Configuracion de la base de
 * datos
 *
 * @author oscar
 *
 */
public class DbConnection {
	/** Parametros de conexion */
	static String bd = "oscar";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost/" + bd;

	private final static Logger log = Logger.getLogger(DbConnection.class);

	Connection connection = null;

	/** Constructor de DbConnection */
	public DbConnection() {
		try {
			// obtenemos el driver de para mysql
			Class.forName("com.mysql.jdbc.Driver");
			// obtenemos la conexión
			this.connection = DriverManager.getConnection(url, login, password);

			if (this.connection != null) {
				log.info("Conexión a base de datos " + bd + " OK\n");
			}
		} catch (SQLException e) {
			log.error("Excepcion SQL al conectarse a base de datos "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error("Excepcion ClassNotFoundException al conectarse a base de datos "
					+ e.getMessage());
		} catch (Exception e) {
			log.error("Excepcion al conectarse a base de datos "
					+ e.getMessage());
		}
	}

	/**
	 * Permite retornar la conexión
	 *
	 * @return connection
	 * */
	public Connection getConnection() {
		return this.connection;

	}

	/** Desconecta la conexión */
	public void desconectar() {
		this.connection = null;
	}
}