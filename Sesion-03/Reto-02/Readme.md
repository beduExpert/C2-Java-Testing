## Kata FizzBuzz parte 2

### OBJETIVO 

- Practicar el uso de TDD a través de su implementación en una Kata de nivel intermedio.

#### REQUISITOS 

1. JDK 8
2. JUnit 5.
3. IDE Eclipse.
4. Primera parte de la Kata FizzBuzz

#### DESARROLLO

En este reto agregaremos un par de requisitos a la Kata FizzBuzz que hicimos en el Ejemplo-02:

 - Se debe imprimir Fizz si un número es múltiplo de 3 o si tiene un 3 en él. 
 - Se debe imprimir Buzz si un número es múltiplo de 5 o si tiene un 5 en él.
 - Se debe imprimir FizzBuzz si un número es múltiplo de 

<details>
	<summary>Solucion</summary>
	
1. Regresa el proyecto KataFizzBuzz que hicimos en el Ejemplo-02



![imagen](img/figura_02.png)
	

10. En la siguiente prueba probarás la funcionalidad core de la Kata, en la que pasando un nombre y apellido se debe regresar el valor con el formato apellido, nombre:

```java
	@Test
	void nombreApellidoRegresaApellidoNombre() {
		assertEquals("Smith, John", NameInverter.invierte("John Smith"));
	}
```


</details> 
