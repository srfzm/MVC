package MVC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Controlador {
	
	public static void principal() throws SQLException
	{
		Modelo mod = Formulario.pedirDato();
		if(mod.validad())
		{
			//insercion
			mod.insertar();
		}
		else
		{
			System.out.println("Cadena vacia");
		}
		
		Formulario.mostrarDatos(mod.devolverUlt());
		
		//System.out.println(pru);
	}
	
	public static void login() throws SQLException
	{
		Modelo mod = Formulario.pedirDato();
		if(!mod.validad())
				return;
		
		if(Modelo.existe(mod))
		{
			Modelo.cargarCliente(mod);
			Ficheros.log("Login correcto");
			Formulario.mostrarDatos(mod);
		}
		else
		{
			Ficheros.log("Login incorrecto");
			System.out.println("El modelo no existe.");
		}
	}
	
	public static void listarClientes() throws SQLException
	{
		HashMap<Integer, Modelo> map = Modelo.devolverClientes();
		Formulario.mostrarLista(map);
	}
	
	public static void pruebaAcc() throws SQLException
	{
		Controlador.listarClientes();
		Controlador.login();
		new Modelo(null, null).devolverUlt();
		Ficheros.mostarLogAccion();
	}

}
