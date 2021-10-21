package curso.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import MVC.prueba;

public class Conexion {
    
	static String bd = "curso";
	static String login = "root";
	static String password = "";
	static String host = "127.0.0.1"; //localhost
	
	static String url = "jdbc:mysql://";
	static Connection conexion; //atributo para  guardar el objeto Connection
        
        
	
    public static Connection getConexion() {
	    if (conexion == null) {
	    	crearConexion();
	    }
	    return conexion;
    }
    
    // devuelve true si se ha creado correctamente
    public static boolean crearConexion() {
	    try {
	        //cargo el driver
	        Class.forName("com.mysql.jdbc.Driver");
	        conexion = DriverManager.getConnection(url + host + "/"+ bd, login, password);
	      
                conexion.setAutoCommit(false);
	        
	    } catch (SQLException e) {
	    	prueba.logger.warn("Error al conectar con la base.",e);
	    	return false;
	    }
	    catch (Exception e) {
	    	prueba.logger.warn("Error al conectar con la base.",e);
	    	return false;
	    }
	    return true;
    }

    public static void desconectar(){
    	try {
            conexion.close();
            conexion = null;
            System.out.println("La conexion a la  base de datos " + bd + " ha terminado");
    	
    	} catch (SQLException e) {
    		prueba.logger.warn("Error al desconectar.",e);
    		System.out.println("Error al cerrar la conexion");
        }
    }
   
}
