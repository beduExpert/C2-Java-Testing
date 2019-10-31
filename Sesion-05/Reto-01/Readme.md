## Reto 01: Completar los casos de prueba de la calculadora

### Objetivo
- Diseñar los casos de prueba necesarios para cubrir las otras 3 operaciones que puede hacer la calculadora del ejemplo 01

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5
5. Ejemplo 01 (Calculadora con palabras)

### Desarrollo
Este reto consiste en agregar al menos 3 casos de prueba necesarios para poder probar el funcionamiento de las operaciones de resta, multiplicación y división que realiza la calculadora del ejemplo. Para ello se seguirá la misma estructura que en el caso de prueba para la suma. No olvides verificar que obtienes los resultados esperados con las pruebas ejecutándolas después de añadirlas.

<details>
	<summary>Solución</summary>
    1. Para poder resolver este reto, es necesario agregar un método por cada caso de prueba, para cada una de las operaciones que vamos a probar. De esta manera, la clase CalculadoraTest nos quedará de la siguiente manera:
	
```java
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
```

</details>
