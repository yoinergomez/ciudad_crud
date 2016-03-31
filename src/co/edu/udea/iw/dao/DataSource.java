package co.edu.udea.iw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import co.edu.udea.iw.util.exception.ExceptionAplication;

/*
 * Facilita obtener conexion con la base de datos
 */
public class DataSource {
	
	private DataSource instancia;
	private Logger log;
	
	public DataSource(){
		
		//Logger para mostrar excepciones
		log = Logger.getLogger(this.getClass());
		
		if(instancia==null){
			
		}
	}
	
	/**
	 * Obtener conexion de la base de datos
	 * @return
	 * @throws ExceptionAplication 
	 */
	public Connection getConnection() throws ExceptionAplication{
		
		Connection con = null;
		
		try{
			
			//Especificar driver de conexiones jdbc ((Importar JAR: mysql-connector-java-5.1.22-bin.jar)
			Class.forName("com.mysql.jdbc.Driver");
			
			//Abrir conexiones
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
			
		} catch(ClassNotFoundException e){
			
			log.error(e);
			throw new ExceptionAplication(e);
			
		} catch (SQLException e) {
			
			log.error(e);
			throw new ExceptionAplication(e);
			
		}
		return con;
	}
}

