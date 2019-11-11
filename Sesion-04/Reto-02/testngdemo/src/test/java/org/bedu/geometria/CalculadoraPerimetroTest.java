package org.bedu.geometria;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CalculadoraPerimetroTest {

	CalculadoraPerimetro calc;

	@BeforeTest
	public void cleanup() {
		calc = new CalculadoraPerimetro();
	}

	@DataProvider(name = "perimetroRectangulo")
	public Object[][] valoresRectangulo() {
		return new Object[][] { { 2.0, 2.0, 8.0 }, { 5.0, 4.0, 18.0 }, { 1.0, 10.0, 22.0 }, { 8.0, 25.0, 66.0 },
				{ 2.0, 9.0, 81.0 } };
	}

	@DataProvider(name = "perimetroCuadrado")
	public Object[][] valoresCuadrado() {
		return new Object[][] { { 4.0, 16.0 }, { 5.0, 20.0 }, { 1.0, 4.0 }, { 8.0, 32.0 }, { 2.0, 8.0 } };
	}

	@DataProvider(name = "perimetroTriangulo")
	public Object[][] valoresTriangulo() {
		return new Object[][] { { 4.0, 4.0, 4.0, 12.0 }, { 5.0, 3.0, 4.0, 12.0 }, { 2.0, 4.0, 5.5, 11.5 },
				{ 10.0, 8.0, 8.0, 26.0 }, { 9.0, 9.0, 11.0, 29.0 } };
	}

	@DataProvider(name = "perimetroCirculo")
	public Object[][] valoresCirculo() {
		return new Object[][] { { 3.0, 3.0 * Math.PI }, { 5.0, 5.0 * Math.PI }, { 10.0, 10.0 * Math.PI },
				{ 15.0, 15.0 * Math.PI }, { 1.0, 1.0 * Math.PI } };
	}

	@Test(dataProvider = "perimetroRectangulo")
	public void testRectangulo(Double base, Double altura, Double resultadoEsperado) {
		Double real = calc.rectangulo(base, altura);
		assertEquals(real, resultadoEsperado);
	}

	@Test(dataProvider = "perimetroCuadrado")
	public void testCuadrado(Double lado, Double resultadoEsperado) {
		Double real = calc.cuadrado(lado);
		assertEquals(real, resultadoEsperado);
	}

	@Test(dataProvider = "perimetroTriangulo")
	public void testTriangulo(Double lado1, Double lado2, Double lado3, Double resultadoEsperado) {
		Double real = calc.triangulo(lado1, lado2, lado3);
		assertEquals(real, resultadoEsperado);
	}

	@Test(dataProvider = "perimetroCirculo")
	public void testCirculo(Double radio, Double resultadoEsperado) {
		Double real = calc.circulo(radio);
		assertEquals(real, resultadoEsperado);
	}
}