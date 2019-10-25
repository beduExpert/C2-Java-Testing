package org.bedu.testing.introduction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SumadorLunarTest {

	@Test
	void sumaLunar() {
		SumadorLunar sumador = new SumadorLunar();
		sumador.sumar(12, 25);
	}

}
