## Ejemplo 03: Aplicando Right-BICEP en la calculadora con palabras

### Objetivo
- Guiarnos en Right-BICEP para asegurar que las pruebas unitarias de la clase Calculadora verifican el comportamiento de ésta con valores no conencionales.

### Desarrollo
Como vimos en el ejemplo y el reto anteriores, las pruebas unitarias nos permiten verificar la funcionalidad de una clase, sin embargo, los casos de prueba que agregamos no nos permiten probar qué pasará en casos extremos como el ingresar una operación que no esté implementada, o pasar parámetros incorrectos a nuestra clase.

1. De acuerdo con Right-BICEP, ya hemos cubierto la primera palabra (Right) pues verificamos con casos correctos qué ocurre si se ingresan valores válidos a nuestra calculadora, por lo que pasaremos a la siguiente letra.
2. Boundary nos dice que tenemos que probar qué ocurre si ingresamos valores extremos, para ello agregaremos un caso de prueba que verificará qué pasa si enviamos un string vacío al método calcular:
```java
@Test
    void probarOperacionStringVacio(){
        //Arrange (no se tienen requisitos específicos para esta prueba)
        //Act y Assert se combinan porque esperamos una excepción como resultado
        assertThrows(InvalidArgumentException.class,
                //Esta operación se consideraría el Act
                () -> calc.calcular("", 2.0, 2.0));
    }
```
La prueba ya nos permite verificar este caso, pero ahora es necesario actualizar la clase para que sea correcta:
```java
public Double calcular(String operacion, Double a, Double b) throws InvalidArgumentException {
        switch (operacion){
            case "sumar":
                return sumar(a, b);
            case "restar":
                return restar(a, b);
            case "multiplicar":
                return multiplicar(a, b);
            case "dividir":
                return dividir(a, b);
            case "":
                throw new InvalidArgumentException(new String[]{"Se esperaba el nombre de la operación"});
        }
        return 0.0;
    }
```

3. Inverse nos pide verificar el resultado invirtiendo la sentencia, para ello realizaremos la suma de un número negativo y un positivo y lo comprobaremos con una resta:
```java
@Test
    void probarSumaNegativoConInverso(){
        //Arrange
        Double esperado = 5.0 - 2.0;
        //Act
        Double resultado = calc.calcular("sumar", 5.0, -2.0);
        //Assert
        assertEquals(esperado, resultado);
    }
```
4. Cross-check nos dice que debemos verificar empleando un método alternativo, por lo que vamos a comprobar el resultado de la multiplicación mediante sumas. Para ello agregaremos el método de prueba y otro auxiliar que calculará la multiplicación con sumas:
```java
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
```

5. Ahora tenemos que forzar una condición de error en nuestra aplicación. Para esto pasaremos uno de los valores numéricos como null a la suma y esperaremos una excepción en el programa:
```java
@Test
    void sumaConValorNuloTest(){
        //Arrange (no se tienen requisitos específicos para esta prueba)
        //Act y Assert se combinan porque esperamos una excepción como resultado
        assertThrows(InvalidArgumentException.class, ()-> calc.calcular("sumar", null, 1.0));
    }
```
Sin embargo, como no está implementada la funcionalidad esta prueba fallará, por lo que debemos modificar la clase para que cumpla con la prueba, agregando el siguiente if al inicio del método calcular:
```java
if(a == null || b == null){
            throw new InvalidArgumentException(new String[]{"Se esperaba el valor para la operación"});
        }
```

6. El último paso es verificar el rendimiento de nuestra aplicación (Performance), para esto agregaremos una aserción que establecerá un tiempo límite de 5ms para realizar el cálculo:
```java
@Test
    void rendimientoSumaTest(){
        //Arrange
        long tiempoEsperadoMs = 5;
        //Act y Assert
        assertTimeout(Duration.ofMillis(tiempoEsperadoMs), ()->calc.calcular("sumar", 2.0, 2.0));
    }
```

7. De esta manera hemos seguido la guía que nos proporciona Right-BICEP para el diseño de pruebas unitarias que cubran diferentes aspectos de nuestra clase y así asegurar su funcionamiento correcto.
