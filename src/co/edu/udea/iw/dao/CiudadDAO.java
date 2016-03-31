package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Ciudad;
import co.edu.udea.iw.util.exception.ExceptionAplication;

/**
 * Expone los metodos CRUD para
 * @author esteban
 *
 */
public interface CiudadDAO {
	/**
	 * Insertar una ciudad en la base de datos,
	 * el atributo codigo debera ser diferente
	 * a los demás registros. 
	 * @param ciudad
	 * @throws ExceptionAplication
	 */
	public void insertar(Ciudad ciudad) throws ExceptionAplication;
	
	/**
	 * Obtiene todas las tuplas de la tabla ciudades
	 * @return
	 * @throws ExceptionAplication
	 */
	public List<Ciudad> obtenerTodas() throws ExceptionAplication;
	
	/**
	 * Permite modificar todos los campos de una ciudad
	 * excepto el atributo codigo que permite identificar
	 * a la ciudad que se hace referencia
	 * 
	 */
	public void modificar(Ciudad ciudad) throws ExceptionAplication;
	
	/*
	 * Elimina una ciudad de la base de datos
	 */
	public void eliminar(Ciudad ciudad) throws ExceptionAplication;
	
	/*
	 * Obtiene una ciudad dado un codigo de ciudad
	 */
	public Ciudad obtener(Long codigo) throws ExceptionAplication;
}
