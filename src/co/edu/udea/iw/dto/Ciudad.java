package co.edu.udea.iw.dto;

/**
 * DTO (Data Transfer Object)
 * Permite la representaciion de los datos 
 * de la tabla ciudad que se obtienen del DataSource
 * @author Yoiner Gomez yoiner.gomez22@gmail.com
 * @version 1
 * @category DTO
 */
public class Ciudad {
	
	private Long codigo;
	private String nombre;
	private String codigoArea;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoArea() {
		return codigoArea;
	}
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}
	
}
