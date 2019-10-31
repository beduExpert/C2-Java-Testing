package org.bedu.testing.nameinverter;

public class NameInverter {
	public static String invierte(String nombre) {
			
		String[] elementosNombre = nombre.trim().split("\\s+");
		
		if(elementosNombre.length > 1)
			return String.format("%s, %s", elementosNombre[1], elementosNombre[0]);
		
		return elementosNombre[0];
	}
}
