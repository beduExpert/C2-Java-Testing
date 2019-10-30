package org.bedu.geometria;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CalculadoraPerimetroTest {

    CalculadoraPerimetro calc;

    @BeforeTest
    public void cleanup(){
        calc = new CalculadoraPerimetro();
    }

    @DataProvider(name = "perimetroRectangulo")
    public Object[][] valoresRectangulo(){
        return new Object[][]{
                {2.0, 2.0, 8.0},
                {5.0, 4.0, 18.0},
                {1.0, 10.0, 22.0},
                {8.0, 25.0, 66.0},
                {2.0, 9.0, 81.0}
        };
    }

    @Test(dataProvider = "perimetroRectangulo")
    public void testRectangulo(Double base, Double altura, Double resultadoEsperado) {
        Double real = calc.rectangulo(base, altura);
        assertEquals(real, resultadoEsperado);
    }


}