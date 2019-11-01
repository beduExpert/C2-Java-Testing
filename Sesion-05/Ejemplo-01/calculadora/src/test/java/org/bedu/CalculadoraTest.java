package org.bedu;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        //Arrange (se incluye en este método para no repetirlo en cada caso de prueba
        calc = new Calculadora();
    }

    @Test
    void calcularSumaTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = 4.0;
        //Act
        Double resultado = calc.calcular("sumar", 2.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void calcularRestaTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = 1.0;
        //Act
        Double resultado = calc.calcular("restar", 5.0, 4.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void calcularMultiplicacionTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = 10.0;
        //Act
        Double resultado = calc.calcular("multiplicar", 5.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void calcularDivisionTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = 1.0;
        //Act
        Double resultado = calc.calcular("dividir", 2.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void operacionStringVacioTest(){
        //Arrange (no se tienen requisitos específicos para esta prueba)
        //Act y Assert se combinan porque esperamos una excepción como resultado
        assertThrows(InvalidArgumentException.class,
                //Esta operación se consideraría el Act
                () -> calc.calcular("", 2.0, 2.0));
    }

    @Test
    void sumaNegativoConInversoTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = 5.0 - 2.0;
        //Act
        Double resultado = calc.calcular("sumar", 5.0, -2.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    @Test
    void multiplicacionConSumasTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = multConSumas(5.0, 4.0);
        //Act
        Double resultado = calc.calcular("multiplicar", 5.0, 4.0);
        //Assert
        assertEquals(esperado, resultado);
    }

    private Double multConSumas(Double x, Double y) {
        Double acumulador = 0.0;
        for(; x > 0; x--){
            acumulador+= y;
        }
        return acumulador;
    }

    @Test
    void sumaConValorNuloTest(){
        //Arrange (no se tienen requisitos específicos para esta prueba)
        //Act y Assert se combinan porque esperamos una excepción como resultado
        assertThrows(InvalidArgumentException.class, ()-> calc.calcular("sumar", null, 1.0));
    }

    @Test
    void rendimientoSumaTest(){
        //Arrange
        long tiempoEsperadoMs = 5;
        //Act y Assert
        assertTimeout(Duration.ofMillis(tiempoEsperadoMs), ()->calc.calcular("sumar", 2.0, 2.0));
    }

    @Test
    void parametrosCambiadosTest() throws InvalidArgumentException {
        //Arrange
        Double esperado1 = 4.0;
        Double esperado2 = 5.0;

        //Act
        Double resultado1 = calc.calcular("dividir", 20.0, 5.0);
        Double resultado2 = calc.calcular("dividir", 20.0, 4.0);
        //Assert
        assertEquals(esperado1, resultado1);
        assertEquals(esperado2, resultado2);
        assertNotEquals(resultado1, resultado2);
    }

    @Test
    void nombreOperacionNulaTest(){
        assertThrows(InvalidArgumentException.class, ()-> calc.calcular(null, 1.0, 1.0));
    }

    @Test
    void dividirEntreCeroTest() throws InvalidArgumentException {
        //Arrange
        Double esperado = Double.NaN;
        //Act
        Double resultado = calc.calcular("dividir", 0.0, 0.0);
        //Assert
        assertEquals(esperado, resultado);
    }
}