package org.bedu.testing.nameinverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class NameInverterTest {
	@Test
	void nullLanzaExcepcion() {
		try {
			
			NameInverter.invierte(null);
			
			fail("Error, se esperaba una Excepcion no lanzada");
		}catch (NullPointerException e) {
		}
	}
	
	@Test
	void nombreVacioEsCadenaVacia() {
		assertEquals("", NameInverter.invierte(""));
	}
	
	@Test
	void nombreSoloRegresaNombre() {
		assertEquals("John", NameInverter.invierte("John"));
	}
	
	@Test
	void nombreApellidoRegresaApellidoNombre() {
		assertEquals("Smith, John", NameInverter.invierte("John Smith"));
	}
	
	@Test 
	void eliminaEspaciosBlancos() {
		assertEquals("Smith, John", NameInverter.invierte("John      Smith   "));
		assertEquals("John", NameInverter.invierte("John      "));
	}
	
	@Test
	void eliminaPrefijo() {
		assertEquals("Smith, John", NameInverter.invierte("Sr. John Smith"));
		assertEquals("Smith, Jane", NameInverter.invierte("Sra. Jane Smith"));
	}
	
	@Test
	void mantenPrefijo() {
		assertEquals("Smith, John Sr.", NameInverter.invierte("Sr. John Smith Sr."));
		assertEquals("Smith, John Sr. PhD.", NameInverter.invierte("Sr. John     Smith Sr. PhD."));
		assertEquals("Smith, John Sr. PhD.", NameInverter.invierte("John Smith Sr. PhD."));
	}
	
}

