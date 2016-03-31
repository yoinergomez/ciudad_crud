package co.edu.udea.iw.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import co.edu.udea.iw.dao.CiudadDAO;
import co.edu.udea.iw.dao.DataSource;
import co.edu.udea.iw.dto.Ciudad;
import co.edu.udea.iw.util.exception.ExceptionAplication;

/**
 * Implementa la clase CiudadDAO para escribir
 * la logica de cada uno de dichos metodos
 * @author esteban
 *
 */
public class CiudadDAOImpl implements CiudadDAO{
	
	/*
	 * Atributos de la clase CiudadDAOImpl
	 */
	private Logger log = Logger.getLogger(this.getClass());
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/*
	 * Constructor de la clase CiudadDAOImpl
	 * 
	 */
	public CiudadDAOImpl() {
		
		conn = null;
		ps = null;
		rs = null;
	}
	
	/**
	 * Insertar ciudad en la base de datos
	 */
	@Override
	public void insertar(Ciudad ciudad) throws ExceptionAplication {
		
		final String SQL_INSERT = "Insert into ciudades "
				+ "(codigo, Nombre, CodigoArea) values (?, ?, ?)";
		
		try{
			//Verificar si la clave primaria está duplicada
			Ciudad ciudadDuplicada = obtener(ciudad.getCodigo());
			if(ciudadDuplicada != null){
				log.error("CiudadDAOImpl.insertar(Ciudad ciudad): "
						+ "Clave primaria duplicada");
				return;
			}
						
			//Reset estado de conexion
			conn = null;
			ps = null;
			rs = null;
			
			//Abrir conexion
			DataSource dataSource = new DataSource();
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_INSERT);
			
			ps.setLong(1, ciudad.getCodigo());
			ps.setString(2, ciudad.getNombre());
			ps.setString(3, ciudad.getCodigoArea());
			
			//Ejecutando SQL
			if(!ps.execute()){
				log.info("Ciudad insertada exitosamente");
			} else{
				log.error("Error al insertar la ciudad");
			}
			
			
		}catch(ExceptionAplication e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			//Cerrar todas las conexiones
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				log.error(e);
				throw new ExceptionAplication(e);
			}
		}
		
	}
	
	/**
	 * Obtiene todas las ciudades que se
	 * encuentran almacenadas en la base de datos
	 * @throws ExceptionAplication 
	 */
	@Override
	public List<Ciudad> obtenerTodas() throws ExceptionAplication{
		//Declaracion de variables
		List<Ciudad> ciudades = new ArrayList<>();
		final String SQL_GET_ALL = "SELECT * FROM ciudades";
		try{
			//Abrir conexiones
			//Reset estado de conexion
			conn = null;
			ps = null;
			rs = null;

			DataSource dataSource = new DataSource();
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_GET_ALL);
			rs = ps.executeQuery();
			
			//Mostrar resultados 
			while(rs.next()){
				Ciudad ciudad = new Ciudad();
				ciudad.setCodigo(rs.getLong("codigo"));
				ciudad.setNombre(rs.getString("nombre"));
				ciudad.setCodigoArea(rs.getString("codigoArea"));
				
				//Agregamos la nueva ciudad a la lista
				ciudades.add(ciudad);
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ExceptionAplication(e);
		} finally {
			//Cerrar todas las conexiones
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				log.error(e);
				throw new ExceptionAplication(e);
			}
		}
		return ciudades;
	}

	@Override
	public void modificar(Ciudad ciudad) throws ExceptionAplication {
		
		//Declaración de variables y constantes
		final String SQL_UPDATE_CIUDAD = "Update ciudades set nombre=?, codigoArea=? WHERE codigo=?";
				
		try{
			Ciudad existe = obtener(ciudad.getCodigo());
			if(existe == null){
				log.error("La ciudad no existe");
				return;
			}
			
			//Reset estado de conexion
			conn = null;
			ps = null;
			rs = null;
			
			//Abrir conexiones
			DataSource dataSource = new DataSource();
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_UPDATE_CIUDAD);
			
			ps.setString(1, ciudad.getNombre());
			ps.setString(2, ciudad.getCodigoArea());
			ps.setLong(3, ciudad.getCodigo());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			log.error(e);
			throw new ExceptionAplication(e);
		} finally {
			//Cerrar todas las conexiones
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				log.error(e);
				throw new ExceptionAplication(e);
			}
		}
		
	}

	@Override
	public void eliminar(Ciudad ciudad) throws ExceptionAplication {
		
		//Declaración de variables y constantes
		final String SQL_DELETE_CIUDAD = "DELETE FROM ciudades WHERE codigo=?";
		
		try{
			
			//Reset estado de conexion
			conn = null;
			ps = null;
			rs = null;
			
			//Abrir conexiones
			DataSource dataSource = new DataSource();
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_DELETE_CIUDAD);
			ps.setLong(1, ciudad.getCodigo());
			ps.execute();
		
		} catch (SQLException e) {
			log.error(e);
			throw new ExceptionAplication(e);
		} finally {
			//Cerrar todas las conexiones
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				log.error(e);
				throw new ExceptionAplication(e);
			}
		}
		
	}

	@Override
	public Ciudad obtener(Long codigo) throws ExceptionAplication {
		
		//Declaración de variables y constantes
		final String SQL_GET_CIUDAD = "SELECT * FROM ciudades WHERE codigo=?";
		Ciudad ciudad = new Ciudad();
		
		try{
			
			//Reset estado de conexion
			conn = null;
			ps = null;
			rs = null;
			
			//Abrir conexiones
			DataSource dataSource = new DataSource();
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SQL_GET_CIUDAD);
			ps.setLong(1, codigo);
			rs = ps.executeQuery();
			
			//Obtener resultado
			if(rs.next()){
				ciudad.setCodigo(rs.getLong("codigo"));
				ciudad.setNombre(rs.getString("nombre"));
				ciudad.setCodigoArea(rs.getString("codigoArea"));
			}else{
				return null;
			}
		
		} catch (SQLException e) {
			log.error(e);
			throw new ExceptionAplication(e);
		} finally {
			//Cerrar todas las conexiones
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch(SQLException e){
				log.error(e);
				throw new ExceptionAplication(e);
			}
		}
		
		
		return ciudad;
	}
	

	
	
}
