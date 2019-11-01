package org.bedu.testing.introduction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumadorTest {

	@Test
	void sumaCorrecta(){
		Sumador sumador = new Sumador();
		int resutado = sumador.sumaEnteros(5, 2);
		
		assertEquals(7, resutado, "El resultado debe ser igual a 7");
	}
}
