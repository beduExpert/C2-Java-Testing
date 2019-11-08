## Reto 02: Diseño de pruebas parametrizables con TestNG
### Objetivo
- Practicar el diseño de pruebas parametrizables con el uso del framework TestNG.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. TestNG 7
5. Ejemplo 02 (Calculadora de áreas y perímetros)

### Desarrollo
Las pruebas parametrizables permiten ahorrar tiempo al momento de generar los casos de prueba para un método de una aplicación, pues permiten que reutilicemos el mismo caso de prueba simplemente variando los parámetros (de ahí su nombre) que estaremos pasando en cada una de las ejecuciones. Estos parámetros incluyen los valores que entrarán al método que probaremos y el resultado que esperamos.

Este reto consiste en la adición de las pruebas parametrizables suficientes para cubrir el resto de la funcionalidad de la calculadora de áreas y perímetros, añadiendo los data provider y métodos de prueba necesarios ello.

Recuerda que las pruebas pueden ser ejecutadas mediante la interfaz del IDE o con el comando `mvn clean test` en la carpeta raíz del proyecto y el data provider consiste en un método que retorna un Object[][] que contiene los valores de entrada y el resultado esperado.

<details>
	<summary>Solución</summary>
    1. Continuaremos con la adición de pruebas para la clase CalculadoraPerimetro, como ya existe el método para probar la función del rectángulo, continuaremos ahora con el cuadrado y el data provider necesario:
```java
@DataProvider(name = "perimetroCuadrado")
    public Object[][] valoresCuadrado(){
        return new Object[][]{
                {4.0, 16.0},
                {5.0, 20.0},
                {1.0, 4.0},
                {8.0, 32.0},
                {2.0, 8.0}
        };
    }

    @Test(dataProvider = "perimetroCuadrado")
    public void testCuadrado(Double lado, Double resultadoEsperado){
        Double real = calc.cuadrado(lado);
        assertEquals(real, resultadoEsperado);
    }
```

    2. Después, probaremos la función del perímetro de un triángulo:
```java
@DataProvider(name = "perimetroTriangulo")
    public Object[][] valoresTriangulo(){
        return new Object[][]{
                {4.0, 4.0, 4.0, 12.0},
                {5.0, 3.0, 4.0, 12.0},
                {2.0, 4.0, 5.5, 11.5},
                {10.0, 8.0, 8.0, 26.0},
                {9.0, 9.0, 11.0, 29.0}
        };
    }

    @Test(dataProvider = "perimetroTriangulo")
    public void testTriangulo(Double lado1, Double lado2, Double lado3, Double resultadoEsperado){
        Double real = calc.triangulo(lado1, lado2, lado3);
        assertEquals(real, resultadoEsperado);
    }
```

    3. Por último para el perímetro, probaremos el círculo:
```java
@DataProvider(name = "perimetroCirculo")
    public Object[][] valoresCirculo(){
        return new Object[][]{
                {3.0, 3.0 * Math.PI},
                {5.0, 5.0 * Math.PI},
                {10.0, 10.0 * Math.PI},
                {15.0, 15.0 * Math.PI},
                {1.0, 1.0 * Math.PI}
        };
    }

    @Test(dataProvider = "perimetroCirculo")
    public void testCirculo(Double radio, Double resultadoEsperado){
        Double real = calc.circulo(radio);
        assertEquals(real, resultadoEsperado);
    }
```
</details>
