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
}
