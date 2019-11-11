package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

class CalculadoraTest {

	private Calculadora calc;

	@BeforeEach
	void setUp() {
		// Arrange (se incluye en este método para no repetirlo en cada caso de prueba
		calc = new Calculadora();
	}

	@Test
	void calcularSumaTest() {
		// Arrange
		Double esperado = 4.0;
		// Act
		Double resultado = calc.calcular("sumar", 2.0, 2.0);
		// Assert
		assertEquals(esperado, resultado);
	}

	@Test
	void calcularRestaTest() {
		// Arrange
		Double esperado = 1.0;
		// Act
		Double resultado = calc.calcular("restar", 5.0, 4.0);
		// Assert
		assertEquals(esperado, resultado);
	}

	@Test
	void calcularMultiplicacionTest() {
		// Arrange
		Double esperado = 10.0;
		// Act
		Double resultado = calc.calcular("multiplicar", 5.0, 2.0);
		// Assert
		assertEquals(esperado, resultado);
	}

	@Test
	void calcularDivisionTest() {
		// Arrange
		Double esperado = 1.0;
		// Act
		Double resultado = calc.calcular("dividir", 2.0, 2.0);
		// Assert
		assertEquals(esperado, resultado);
	}

	@Test
	void probarOperacionStringVacio() {
		// Arrange (no se tienen requisitos específicos para esta prueba)
		// Act y Assert se combinan porque esperamos una excepción como resultado
		assertThrows(IllegalArgumentException.class,
				// Esta operación se consideraría el Act
				() -> calc.calcular("", 2.0, 2.0));
	}

	@Test
	void probarSumaNegativoConInverso() {
		// Arrange
		Double esperado = 5.0 - 2.0;
		// Act
		Double resultado = calc.calcular("sumar", 5.0, -2.0);
		// Assert
		assertEquals(esperado, resultado);
	}

	@Test
	void multiplicacionConSumasTest() throws IllegalArgumentException {
		// Arrange
		Double esperado = multConSumas(5.0, 4.0);
		// Act
		Double resultado = calc.calcular("multiplicar", 5.0, 4.0);
		// Assert
		assertEquals(esperado, resultado);
	}

	@Test
	void sumaConValorNuloTest() {
		// Arrange (no se tienen requisitos específicos para esta prueba)
		// Act y Assert se combinan porque esperamos una excepción como resultado
		assertThrows(IllegalArgumentException.class, () -> calc.calcular("sumar", null, 1.0));
	}

	@Test
	void rendimientoSumaTest() {
		// Arrange
		long tiempoEsperadoMs = 5;
		// Act y Assert
		assertTimeout(Duration.ofMillis(tiempoEsperadoMs), () -> calc.calcular("sumar", 2.0, 2.0));
	}

	private Double multConSumas(Double x, Double y) {
		Double acumulador = 0.0;
		for (; x > 0; x--) {
			acumulador += y;
		}
		return acumulador;
	}
}