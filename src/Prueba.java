import java.util.List;

import org.apache.log4j.BasicConfigurator;

import co.edu.udea.iw.dao.CiudadDAO;
import co.edu.udea.iw.dao.impl.CiudadDAOImpl;
import co.edu.udea.iw.dto.Ciudad;
import co.edu.udea.iw.util.exception.ExceptionAplication;

public class Prueba {

	//Declaracion de variables
	static List<Ciudad> lista = null;
	static CiudadDAO dao = new CiudadDAOImpl();
	
	public static void main(String[] args) {
		
		
		try{
			
			//Configuracion basica log4j
			BasicConfigurator.configure();

			//Prueba mostrar ciudades
			System.out.println("Prueba mostrar ciudades");
			mostrar();
			
			//Prueba insertar ciudad
			System.out.println("\n");
			System.out.println("Prueba insertar ciudad");
			Ciudad ciudad = new Ciudad();
			Long codigo = (long) 111;
			ciudad.setNombre("Cartagen");
			ciudad.setCodigoArea("p2p");
			ciudad.setCodigo(codigo);
			dao.insertar(ciudad);
			mostrar();
			
			//Prueba obtener ciudad
			System.out.println("\n");
			System.out.println("Prueba obtener ciudad");
			ciudad = dao.obtener(codigo);
			System.out.println("Nombre: "+ciudad.getNombre());
			
			//Prueba modificar ciudad
			System.out.println("\n");
			System.out.println("Prueba modificar ciudad");
			ciudad.setNombre("Cartagena");
			ciudad.setCodigoArea("pla");
			ciudad.setCodigo(codigo);
			dao.modificar(ciudad);
			mostrar();
			
			//Prueba eliminar ciudad
			System.out.println("\n");
			System.out.println("Prueba eliminar ciudad");
			dao.eliminar(ciudad);
			mostrar();
			
		} catch(ExceptionAplication e){
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static void mostrar() throws ExceptionAplication{
		lista = dao.obtenerTodas();
		for(Ciudad c:lista){
			System.out.println("Nombre: "+c.getNombre());
		}
	}

}
