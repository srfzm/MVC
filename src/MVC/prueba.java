package MVC;

import curso.bbdd.Conexion;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class prueba {

	private Connection con;
	private Statement stm;
	private ResultSet res;
	
	public static Logger logger = LogManager.getLogger(prueba.class);
	
	prueba()
	{
		con = Conexion.getConexion();
		
		try
		{
			
			if(con==null)
			{
				System.out.println("No hay conexion");
				return;
			}
			
			stm = con.createStatement();
			
			res = stm.executeQuery("select * from tabla1");
			
			while(res.next())
			{
				System.out.println("Fila: "+res.getInt(1)+" "+res.getString(2)+" "+res.getInt(3));
			}
			
			Conexion.desconectar();
		}
		catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
	}
	
	public static void main (String [ ] args) {
		//prueba p= new prueba();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
		
		logger.info("Iniciada aplicación.");
		
		try
		{
			//Controlador.principal();
			//Controlador.login();
			//Controlador.listarClientes();
			Controlador.pruebaAcc();
		}
		catch(SQLException e) {

            // TODO Auto-generated catch block
			logger.warn("Error SQL.");
            e.printStackTrace();
		}
		
		logger.info("Salida aplicación.");
	}
}
