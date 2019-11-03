package org.bedu.testing.stringcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

	private StringCalculator calculator = new StringCalculator();
	
	@Test
	void cadenaVacia() {
		assertEquals(0, calculator.add(""));
	}
	
	@Test
	void numeroSoloRegresaMismoNumero() {
		assertEquals(5, calculator.add("5"));
		assertEquals(8, calculator.add("8"));
		assertEquals(2, calculator.add("2"));
	}
	
	@Test
	void numerosSeparadosPorComas() {
		assertEquals(3, calculator.add("1,2"));
		assertEquals(4, calculator.add("1,3"));
	}
	
	@Test
	void excepcionConMensaje() {
		try {
			calculator.add("1,-2,5");
			fail("La aplicación no debe llegar a este punto");
		}catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage().contains("no se admiten números negativos"));
		}
	}
	
	@Test 
	void excepcionConMensajeYValor() {
		try {
			calculator.add("1,-2,5");
			fail("La aplicación no debe llegar a este punto");
		}catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage().contains("no se admiten números negativos [-2]"));
		}
	}

	@Test 
	void excepcionConMensajeYValorMultiplesNumeros() {
		try {
			calculator.add("1,-2,5,-7,-15");
			fail("La aplicación no debe llegar a este punto");
		}catch (IllegalArgumentException ex) {
			assertTrue(ex.getMessage().contains("no se admiten números negativos [-2,-7,-15]"));
		}
	}
	
}
