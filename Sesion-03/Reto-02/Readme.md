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

2. Agrega la prueba para validar el funcionamiento del primer requerimiento. 

```java
	@Test
	void treceEsFizz() {
		assertEquals("Fizz", FizzBuzz.valorSecuencia(13));
	}
```

Si ejecutas la prueba esta debe fallar:

![imagen](img/figura_01.png)
	
3. Escribe el código para que la prueba pase de manera correcta:

```java
	public static String valorSecuencia(int valor) {

		String regreso = "";
		
		if(valor == 0) {
			return "0";
		}
		
		if(valor % 3 == 0 || String.valueOf(valor).contains("3")) {
			regreso +=  "Fizz";
		} 
		
		if(valor % 5 == 0) {
			regreso += "Buzz";
		}
		
		
		return regreso.isEmpty() ? String.valueOf(valor) : regreso;
	}
```

Ejecuta nuevamente la prueba para comprobar que se ejecuta de forma correcta.

4. Escribe la prueba para validar el segundo requisito:

```java
	@Test
	void veinticincoEsBuzz() {
		assertEquals("Buzz", FizzBuzz.valorSecuencia(25));
	}
```

Ejecuta nuevamente la prueba... esta debe pasar sin problema porque todos los números que contienen un 5 son múltiplos de 5, por lo que ya tenemos cubierto ese caso =)


5. Agrega el código para valir el último requerimmiento:
```java
	@Test
	void treintaycincoEsFizzBuzz() {
		assertEquals("FizzBuzz", FizzBuzz.valorSecuencia(35));
	}
```

6. Escribe el código para ue la prueba se ejecute de forma correcta.

</details> 
