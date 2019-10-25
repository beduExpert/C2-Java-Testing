## Ejecutando una prueba exitosa

### OBJETIVO 

- Realizar las modificaciones necesarias en el código del ejemplo anterior, para que la prueba se ejecute de forma exitosa, haciendo uso de la clase SumadorLunar. 

#### REQUISITOS 

1. Ejemplo anterior funcionando.

#### DESARROLLO

Modifica la clase SumadorLunarTest de forma que la prueba deje de fallar, obteniendo una salida como la siguiente:

![imagen](img/figura_01.png)


<details>
<summary>Solucion</summary>
<p>2. Modifica la clase SumadorLunarTest eliminando la línea: </p>

	fail("Not yet implemented");

<p>3. Crea una nueva instanacia de la clase SumadorLunar dentro del método sumaLunar </p>

	void sumaLunar() {
		SumadorLunar sumador = new SumadorLunar();
	}

<p>4. Invoca el método suma de la instancia sumador, pasando cualquier par de números enteros como parámetros</p>

	sumador.sumar(12, 25);
	
<p>5. Abre la clase SumadorLunarTest, ve al botón RunAs -> JUnit Test</p>
</details> 
