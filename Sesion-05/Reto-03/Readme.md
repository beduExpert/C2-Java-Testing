## Reto 03: Asegurar el cumplimiento de Right-BICEP y CORRECT

### Objetivo
- Asegurar que los casos de prueba para una aplicación cumplan con los requisitos marcados por Right-BICEP y CORRECT para asegurar una buena cobertura del código.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5
5. Ejemplo 03 (Calculadora con palabras después de aplicar Right-BICEP)

### Desarrollo
En el ejemplo 3 se agregaron casos de prueba para asegurar que se cubrieran las indicaciones dadas por Right-BICEP, pero no se verificó que las entradas cumplieran con todo lo que requiere CORRECT para asegurar que los datos con los que se realizan las pruebas cubren todos los casos. Algunos casos que te pueden ayudar son:
1. ¿Se obtienen resultados correctos si se alteran los parámetros para la división o la resta?
2. ¿Qué pasa si se envía un String nulo como nombre de operación?
3. ¿Se obtiene un valor correcto al dividir entre 0?

Considera esos casos y otros que detectes, identificando a qué letra de la sigla CORRECT corresponden y agrégalos a nuestros casos de prueba.

<details>
	<summary>Solución</summary>
    Se incluye la respuesta a las 4 preguntas planteadas en el desarrollo, junto con el código necesario para agregar una prueba para cada caso:
    1. Con la implementación actual sí, pero no existe ninguna prueba que nos ayude a comprobarlo.
    2. Se obtiene un NullPointerException no esperado, por lo que debemos incluir la corrección necesaria para lanzar un InvalidArgumentException.
    3. Se debe obtener el valor NaN (not a number) debido a que no existe ese valor, pero tampoco existe la prueba que lo compruebe.

```java
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
```

Además hay que agregar la siguiente condición al inicio del método calcular:

```java
if(operacion == null){
            throw new InvalidArgumentException(new String[]{"Se esperaba el nombre de la operación"});
        }
```
</details>
