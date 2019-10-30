package org.bedu.geometria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraPerimetroTest {

    CalculadoraPerimetro calc;

    @BeforeEach
    public void cleanup(){
        calc = new CalculadoraPerimetro();
    }
    @Test
    public void rectanguloTest(){
        double resultado = calc.rectangulo(2, 4);
        assertEquals(15, resultado);
    }
}