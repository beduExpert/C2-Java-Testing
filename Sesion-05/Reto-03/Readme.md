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
Se incluye la respuesta a las 3 preguntas planteadas en el desarrollo, junto con el código necesario para agregar una prueba para cada caso:
	
1. Con la implementación actual no existe el problema de obtener valores incorrectos alterando el orden de los parámetros de entrada, pero no existe ninguna prueba que nos ayude a comprobarlo, así que sería neceseario añadir un método como el siguiente que verifique que el orden (letra O de la sigla):

```java
@Test
    void parametrosCambiadosTest() throws InvalidArgumentException {
        //Arrange
        Double esperado1 = 10.0;
        Double esperado2 = 0.1;

        //Act
        Double resultado1 = calc.calcular("dividir", 40.0, 4.0);
        Double resultado2 = calc.calcular("dividir", 4.0, 40.0);
        //Assert
        assertEquals(esperado1, resultado1);
        assertEquals(esperado2, resultado2);
        assertNotEquals(resultado1, resultado2);
    }
```

2. Al pasar un String nulo como nombre de la operación a calcular se obtiene un **NullPointerException** no esperado, por lo que debemos incluir la corrección necesaria para verificar si se ingresa este valor e informar al usuario qué está pasando, además de incluir la prueba necesaria para validar este comportamiento (considerando que también validamos el caso de que el valor del String no sea alguna de las 4 operaciones cubriremos las letras C y E de la sigla):

La prueba quedaría de la siguiente manera:
```java
@Test
    void nombreOperacionNulaTest(){
        assertThrows(InvalidArgumentException.class, ()-> calc.calcular(null, 1.0, 1.0));
    }
```

Y el método calcular:

```java
public Double calcular(String operacion, Double a, Double b) throws InvalidArgumentException {
        if(a == null || b == null){
            throw new InvalidArgumentException(new String[]{"Se esperaba el valor para la operación"});
        }
        if(operacion == null){
            throw new InvalidArgumentException(new String[]{"Se esperaba el nombre de la operación"});
        }
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
```

3. Si tratamos de dividir cualquier número entre 0 se debe obtener el valor **NaN** (not a number) debido a que no existe ese valor, pero tampoco existe la prueba que lo compruebe, así que debemos añadirla y no simplemente confiar en que el lenguaje se encargará de ello (con esto cubriremos la letra R de rango).

```java
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
</details>
