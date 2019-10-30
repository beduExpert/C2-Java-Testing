## Calculadora de área y perímetro
### Objetivo
- Aplicar de manera práctica los conocimientos recién adquiridos sobre el framework de pruebas JUnit

#### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5

### Desarrollo
1. Dentro de la carpeta **junitdemo** se encuentra un proyecto de Java, que permite calcular el área y el perímetro de las siguientes figuras: cuadrado, rectángulo, triángulo y círculo.
2. Este proyecto está configurado para usar JUnit5 como su framework de pruebas y ya se han generado los métodos para probar la funcionalidad de área y perímetro para el cuadrado.
3. Se incluyen también métodos de inicialización (llamados **cleanup**) para crear un nuevo objeto antes de la ejecución de cada prueba, asegurando que las pruebas anteriores no afecten el resultado de la actual.
4. Los métodos de prueba se encuentran en las clases **CalculadoraPerimetroTest** y **CalculadoraAreaTest** y están anotados con **@Test** para indicarle a JUnit que esos métodos son para realizar la prueba de alguna funcionalidad, sin embargo, su implementación es incorrecta y causan que la prueba falle cuando no debería.
5. Con ayuda de tu IDE, navegando hasta las clases de prueba y ejecutando los métodos o con el comando `mvn clean test` ejecuta las pruebas y analiza el resultado. ¿Cuál es el error en la implementación?
6. Realiza la corrección en los valores esperados y ejecuta una vez más las pruebas, validando que ahora pasen sin problemas.

De esta manera se ha realizado una prueba simple prueba unitaria empleando JUnit 5.

