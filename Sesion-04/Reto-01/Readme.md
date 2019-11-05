## Reto 01: Diseño de pruebas unitarias con JUnit

### Objetivo
- Continuar con el aprendizaje de JUnit 5 agregando casos de prueba a la calculadora de áreas y perímetros.

### Requisitos
1. JDK 8 o superiór
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5
5. Ejemplo-01: calculadora de áreas y perímetros

### Desarrollo
Continuando con lo realizado en el Ejemplo 01, agregaremos los casos de prueba necesarios para asegurar una cobertura al 100% del código. Para ello, se tendrán que agregar los casos de prueba que cubran todos los métodos para calcular áreas y perímetros de las figuras, y además al diseñar las pruebas hay que considerar los siguientes casos junto con la respuesta que esperamos:

 - Valores válidos
 - Valores extremos (entendidos como valores muy grandes o muy pequeños)
 - Valores no válidos (como valores nulos o no aceptados por las funciones)
 - Caminos incorrectos (en el caso de condicionales y switches)

Verificar que los resultados obtenidos con las pruebas sean válidos, incluyendo que fallen cuando sea esperado un fallo indicando qué valor era esperado.

<details>
	<summary>Solución</summary>
  
  <p>1. Lo primero que hay que hacer es agregar algunas pruebas que permitan validar los valores válidos para el cálculo del área del cuadrado. En este caso usaremos la guía del inicio del desarrollo del reto para saber qué probar. </p>
  
  Lo primero que se indica es los valores válidos. En el área no podemos tener valores negativos de en los lados, entonces validaremos que el valor de **lado** no sea negativo. En caso de que lo sea podemos lanzar una IllealArgumentException.

```java  
    @Test()
    void rectanguloAreaNegativa() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		calc.cuadrado(-10);
    	});
    }
```

**assertThrows** valida que se haya lanzado la excepción indicada como primer argumento. En este caso verificamos que se lance una **IllegalArgumentException**.
  
  
Si ejecutas la prueba esta deberá fallar ya que no se está tomando en cuenta este requerimiento. 
  
  2. Agregaremos el código que haga que pase la prueba, validando que el valor sea mayor a cero, de lo contrario se lanzará la excepción.

```java
	public double cuadrado(double lado){    	
		if(lado < 0) {
	    		throw new IllegalArgumentException("No se admiten números negativos.");
	    	}
	    	
	        return lado * lado;
	}
```

3. Ahora agrega una validación para la segunda recomendación, en donde probaremos los valores extremos. En este caso ya que el valor pequeño podemos usar el 0 (ya que ya validamos los números negativos). En el caso de los valores grandes probaremos el valor máximo de los enteros, representado por la constante ***Integer.MAX_VALUE***.

Separa estos en dos casos de pruebas independientes, el primero para el cero, el cual esperamos que lance también una IllegalArgumentException:

```java
    @Test
    void rectanguloLimitesMinimos() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		calc.cuadrado(0);
    	});
    }
```

Si ejecutas la prueba esta debe fallar.

4. Agrega el código que haga que la prueba pase, en este caso ya estamos validando los números negativos así que agregar esta funcionalidad debe ser tan simple como modificar la condición para que contemple también el cero:

```java
	public double cuadrado(double lado) {

		if (lado <= 0) {
			throw new IllegalArgumentException("No se admiten números negativos.");
		}

		return lado * lado;
	}
```
Ejecuta la prueba y esta debe pasar correctamente.



</details>
