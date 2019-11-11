package org.bedu;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {

	Factorial factorial;

	@BeforeTest
	public void cleanup() {
		factorial = new Factorial();
	}

	@Test
	public void testFactorialCero() {
		long resultado = factorial.calcular(0);
		assertEquals(resultado, 1);
	}

	@Test
	public void testFactorialDiez() {
		long resultado = factorial.calcular(10);
		assertEquals(resultado, 3628800);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFactorialMayor() {
		factorial.calcular(21);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFactorialNegativo() {
		factorial.calcular(-1);
	}
}