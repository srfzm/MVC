package MVC;

import java.io.BufferedWriter;
import java.io.*;

public class Ficheros {
	
	private final static String RUTA_LOG="log.txt";
	private final static String RUTA_ACN="accion.txt";
	
	public static void escribir(String texto, String ruta)
	{
		FileWriter fich = null;
        try {

           fich = new FileWriter(ruta,true);
           BufferedWriter bufferedWriter = new BufferedWriter(fich);
           
           bufferedWriter.write(texto+"\n");

           bufferedWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public static void log(String texto)
	{	
		Ficheros.escribir(texto, RUTA_LOG);
	}
	
	public static void accion(String texto)
	{	
		Ficheros.escribir(texto, RUTA_ACN);
	}
	
	public static void mostarLogAccion() {
        try {
        	
            File fich = new File(RUTA_ACN);
            
            if(!fich.exists())
            {
            	System.out.println("El fichero accion no esta creado");
            	return;
            }
            
            FileReader reader = new FileReader(fich);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
