
## Complejidad Ciclomática

### OBJETIVO

- Calcular la complejidad ciclomática de un bloque de código.

#### DESARROLLO

1. Dibujar el gráfico de flujo del siguiente bloque de pseudo-código, el número a la izquierda de la instucción representa esa instrucción en el grafo:

		1) WHILE NOT final DO
			2) leer
			3) IF campo 1=0 THEN
				4) procesar()
				5) incrementar_contador()
			6) ELSE IF campo 1=1 THEN
				7) reinicia_contador()
			8) ELSE
				9) procesar()
			10) END IF
		11) END WHILE

![imagen](img/figura_01.png)

2. Calcular la complejidad ciclomática usando el método del número de regiones. Para ver claramente cada región pondremos cada una de un color diferente.

![imagen](img/figura_02.png)

En el ejemplo anterior, la complejidad ciclomática es 4.


3. Corroborar el valor anterior usando el número de nodos y aristas. A modo de recordatorio, la formula es la siguiente:

		V(G) = E - N + 2, donde
		E = Número de Aristas
		N = Número de nodos.
		
Tenemos que:
		E = 13
		N = 11
	
Por lo tanto:

		V(G) = 13 - 11 + 2
		V(G) = 2 + 2
		V(G) = 4
		
En el ejemplo anterior podemos comprobar que la complejidad ciclomática es 4.

4. Corroborar los valores anteriores usando el método de los nodos predicado. Recuerda que los nodo predicado son aquellos nodos de condición, o los nodos de los que se despreden otros dos o más nodos.

La formula para el cálculo de la complejidad ciclomática usando los nodos pedicado es:

		V(G) = P + 1, donde
		P = Número de nodos predicado
		
En el grafo podemos ver que existen 3 nodos predicado: los nodos 1, 3 y 6. Por lo tanto

		V(G) = 3 + 1
		V(G) = 4
		
En el ejemplo anterior podemos comprobar que la complejidad complejidad ciclomática es 4.

		
