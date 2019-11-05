package org.bedu.geometria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class CalculadoraAreaTest {

    CalculadoraArea calc;

    @BeforeEach
    public void cleanup(){
        calc = new CalculadoraArea();
    }

    @Test
    public void rectanguloTest(){
        double resultado = calc.rectangulo(2, 4);
        assertEquals(8, resultado);
    }
    
    @Test()
    void rectanguloAreaNegativa() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		calc.cuadrado(-10);
    	});
    }
    
    @Test
    void rectanguloLimitesMinimos() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		calc.cuadrado(0);
    	});
    }
}