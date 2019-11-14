
## Postwork 02: Complejidad Ciclomática aplicada a tu proyecto

### OBJETIVO

- Calcular la complejidad ciclomática del código de tu proyecto.
- Determinar el valor usando distintos métodos.

#### DESARROLLO

Calcularás la complejidad ciclomática de algunos de los bloques de código más importantes de tu proyecto.

1. Abre el archivo **GeneradorDeContrasenias** del proyecto que se encuentra en el repositorio y busca los dos metodos **generaTodasLasContrasenias** y **generaPassword**

1. Etiqueta cada una de las instrucciones del siguiente código.

Tip: El código contiene además de instrucciones secuenciales (que se ejecutan siempre una después de la otra, ¿cómo debes representar estas instrucciones?).

		while (a < 3){
			if (b < 1){
				c++;
			}
			if (a > b) {
				b = a;
			}
			else if (c == 3){
				c++;
			}
			else {
				a++;
			}
		}


![imagen](img/figura_01.png)


2. Dibuja el gráfico de flujo:

![imagen](img/figura_02.png)


3. Calcula la complejidad ciclomática usando el número de nodos y aristas. A modo de recordatorio, la fórmula es la siguiente:

		V(G) = E - N + 2, donde
		E = Número de Aristas
		N = Número de nodos.
		
Tenemos que:
		E = 14
		N = 11
	
Por lo tanto:

		V(G) = 14 - 11 + 2
		V(G) = 3 + 2
		V(G) = 5
		
En el ejemplo anterior podemos comprobar que la complejidad ciclomática es 5.

4. Corrobora el valor anterior usando el método de los nodos predicado. Recuerda que los nodo predicado son aquellos nodos de condición, o los nodos de los que se desprenden otros dos o más nodos.

La fórmula para el cálculo de la complejidad ciclomática usando los nodos predicado es:

		V(G) = P + 1, donde
		P = Número de nodos predicado
		
En el grafo podemos ver que existen 4 nodos predicado: los nodos B, C, E y G. Por lo tanto:

		V(G) = 4 + 1
		V(G) = 5
		
Con esto, podemos comprobar que la complejidad ciclomática es 5.
