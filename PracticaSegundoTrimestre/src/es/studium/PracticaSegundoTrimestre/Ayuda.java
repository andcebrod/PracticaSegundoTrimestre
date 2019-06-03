package es.studium.PracticaSegundoTrimestre;

import java.io.IOException;

public class Ayuda {

	
	public Ayuda() 
	
	{
		try
		{
		Runtime.getRuntime().exec("hh.exe AyudaPGestion/AyudaGestion.chm");
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}

	}

}
