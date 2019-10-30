## Diseño de pruebas unitarias con JUnit
### Objetivo
- Continuar con el aprendizaje de JUnit 5 agregando casos de prueba a la calculadora de áreas y perímetros.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5
5. Ejemplo-01 (calculadora de áreas y perímetros)

### Desarrollo
1. Continuando con lo realizado en el Ejemplo 01, agregaremos los casos de prueba necesarios para asegurar una cobertura al 100% del código.
2. Para ello, se tendrán que agregar los casos de prueba que cubran todos los métodos para calcular áreas y perímetros de las figuras, y además al diseñar las pruebas hay que considerar los siguientes casos junto con la respuesta que esperamos:
<pre>
Valores válidos
Valores extremos (entendidos como valores muy grandes o muy pequeños)
Valores no válidos (como valores nulos o no aceptados por las funciones)
Caminos incorrectos (en el caso de condicionales y switches)
</pre>
3. Una vez desarrolladas las pruebas, ejecutarlas mediante el IDE o con el comando `mvn clean test` en la carpeta raíz del proyecto.
4. Verificar que los resultados obtenidos con las pruebas sean válidos, incluyendo que fallen cuando sea esperado un fallo indicando qué valor era esperado.
