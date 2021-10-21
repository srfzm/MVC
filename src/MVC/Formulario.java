package MVC;

import java.util.HashMap;
import java.util.Scanner;

public class Formulario {
	
	public static Modelo pedirDato()
	{
		String nombre;
		String telefono;
		Modelo mod;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Introduce nombre");
		nombre = in.next();
		
		System.out.println("Introduce telefono");
		telefono = in.next();
				
		mod = new Modelo(nombre,telefono);
		
		in.close();
		
		return mod;
	}
	
	public static void mostrarDatos(Modelo datos)
	{
		if(datos!=null)
			System.out.println(datos.toString());
	}
	
	public static void mostrarLista(HashMap<Integer, Modelo> map)
	{
		for(Modelo mod : map.values())
		{
			Formulario.mostrarDatos(mod);
		}
	}

}
