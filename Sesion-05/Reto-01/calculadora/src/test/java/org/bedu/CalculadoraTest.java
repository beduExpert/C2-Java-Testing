package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        //Arrange (se incluye en este método para no repetirlo en cada caso de prueba
        calc = new Calculadora();
    }

    @Test
    void calcularSumaTest() {
        //Arrange
        Double esperado = 4.0;
        //Act
        Double resultado = calc.calcular("sumar", 2.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void calcularRestaTest() {
        //Arrange
        Double esperado = 1.0;
        //Act
        Double resultado = calc.calcular("restar", 5.0, 4.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void calcularMultiplicacionTest() {
        //Arrange
        Double esperado = 10.0;
        //Act
        Double resultado = calc.calcular("multiplicar", 5.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void calcularDivisionTest() {
        //Arrange
        Double esperado = 1.0;
        //Act
        Double resultado = calc.calcular("dividir", 2.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }
}