package org.bedu.geometria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraAreaTest {

    CalculadoraArea calc;

    @BeforeEach
    public void cleanup(){
        calc = new CalculadoraArea();
    }

    @Test
    public void rectanguloTest(){
        double resultado = calc.rectangulo(2, 4);
        assertEquals(10, resultado);
    }
}