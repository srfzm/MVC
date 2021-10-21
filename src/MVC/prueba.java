package MVC;

import curso.bbdd.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class prueba {

	private Connection con;
	private Statement stm;
	private ResultSet res;
	
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
		try
		{
			//Controlador.principal();
			//Controlador.login();
			//Controlador.listarClientes();
			Controlador.pruebaAcc();
		}
		catch(SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
		}
	}
}
