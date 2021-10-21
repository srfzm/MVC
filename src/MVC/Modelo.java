package MVC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import curso.bbdd.Conexion;

public class Modelo {

	private String nombre;
	private String telefono;
	
	public Modelo(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public boolean validad()
	{
		if(nombre.equals("") || telefono.equals("") || nombre==null || telefono == null)
			return false;
		else
			return true;
	}
	
	public boolean insertar() throws SQLException
	{
		Connection conexion = Conexion.getConexion();
		if(conexion==null)
		{
			System.out.println("No hay conexion");
			return false;
		}
		
//		try
//		{
			PreparedStatement ps = conexion
                .prepareStatement("INSERT INTO cliente (nombre, telefono) " + "VALUES (?, ?)");

			ps.setString(1, nombre);
			ps.setString(2, telefono);
			int resultado = ps.executeUpdate();

			if (resultado == 0) {
				System.out.println("NO se ha podido insertar");
				Ficheros.accion("NO se ha podido insertar");
			}
			else
			{
				System.out.println("Inserccion correcta");
				Ficheros.accion("Inserccion correcta");
			}
			conexion.commit();

			ps.close();
			
			Conexion.desconectar();
			
			return true;
//		}
//		catch(SQLException e) {
//
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            if (conexion != null) {
//                try {
//                    conexion.rollback();
//                } catch (SQLException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//            }
//		}
		
//		return false;
	}
	
	public Modelo devolverUlt() throws SQLException
	{
		Connection conexion = Conexion.getConexion();
		if(conexion==null)
		{
			System.out.println("No hay conexion");
			return null;
		}
		
//		try
//		{
			String cons = "SELECT nombre, telefono FROM cliente ORDER BY id DESC LIMIT 1";
			PreparedStatement ps = conexion
                .prepareStatement(cons);

//			ps.setString(1, nombre);
//			ps.setString(2, telefono);
//			int resultado = ps.executeUpdate();
			
			ResultSet resultado = ps.executeQuery();
			
			resultado.next();

			Modelo dev = new Modelo(resultado.getString("nombre"),resultado.getString("telefono"));
			if(dev!=null)
				Ficheros.accion("Se ha devuelto el ultimo.");

			ps.close();
			
			Conexion.desconectar();
			
			return dev;
//		}
//		catch(SQLException e) {
//
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            if (conexion != null) {
//                try {
//                    conexion.rollback();
//                } catch (SQLException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//            }
//		}
		
//		return null;
	}
	
	public static boolean existe(Modelo cliente) throws SQLException
	{
		boolean dev=false;
		
		Connection conexion = Conexion.getConexion();
		if(conexion==null)
		{
			System.out.println("No hay conexion");
			return false;
		}
		
		String cons = "SELECT nombre, telefono FROM cliente WHERE telefono=?";
		PreparedStatement ps = conexion
            .prepareStatement(cons);
		ps.setString(1, cliente.telefono);

//		ps.setString(1, nombre);
//		ps.setString(2, telefono);
//		int resultado = ps.executeUpdate();
		
		ResultSet resultado = ps.executeQuery();
		
		//resultado.next();

		//Modelo tmp = new Modelo(resultado.getString("nombre"),resultado.getString("telefono"));
		
		Ficheros.accion("Se ha realizado una busqueda en cliente");
		if(resultado.next()!=false)
			dev=true;

		ps.close();
		
		Conexion.desconectar();
		
		return dev;
		
	}
	
	public static void cargarCliente(Modelo cliente) throws SQLException
	{
		Connection conexion = Conexion.getConexion();
		if(conexion==null)
		{
			System.out.println("No hay conexion");
			return;
		}
		
		String cons = "SELECT nombre, telefono FROM cliente WHERE telefono=?";
		PreparedStatement ps = conexion
            .prepareStatement(cons);

		ps.setString(1, cliente.telefono);
		
		ResultSet resultado = ps.executeQuery();
		
		resultado.next();

		cliente.nombre=resultado.getString("nombre");
		cliente.telefono=resultado.getString("telefono");

		ps.close();
		
		Conexion.desconectar();
		
		
	}
	
	public static HashMap<Integer, Modelo> devolverClientes() throws SQLException
	{
		Connection conexion = Conexion.getConexion();
		if(conexion==null)
		{
			System.out.println("No hay conexion");
			return null;
		}
		
		String cons = "SELECT * FROM cliente";
		PreparedStatement ps = conexion
            .prepareStatement(cons);
		
		ResultSet resultado = ps.executeQuery();
		
		HashMap<Integer, Modelo> map = new HashMap<Integer,Modelo>();
		
		while(resultado.next())
		{
			map.put(resultado.getInt("id"), new Modelo(resultado.getString("nombre"),resultado.getString("telefono")));
		}

		ps.close();
		
		Conexion.desconectar();
		
		Ficheros.accion("Se ha obtenido el listado de clientes");
		
		return map;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Modelo: Nombre "+nombre+" Telefono "+telefono+".");
	}
}
