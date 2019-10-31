package org.bedu.testing.nameinverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameInverter {
	public static String invierte(String nombre) {

		List<String> elementosNombre = new ArrayList<String>(Arrays.asList(nombre.trim().split("\\s+")));

		if (esHonorifico(elementosNombre.get(0))) {
			elementosNombre.remove(0);
		}

		String postfijo = "";

		if (elementosNombre.size() > 2) {
			for (String pf : elementosNombre.subList(2, elementosNombre.size())) {
				postfijo += pf + " ";
			}
		}

		if (elementosNombre.size() > 1)
			return String.format("%s, %s %s", elementosNombre.get(1), elementosNombre.get(0), postfijo).trim();

		return elementosNombre.get(0);
	}

	private static boolean esHonorifico(String elemento) {
		List<String> honorificos = new ArrayList<String>(Arrays.asList(new String[] { "Sr.", "Sra." }));

		return honorificos.contains(elemento);
	}

}
