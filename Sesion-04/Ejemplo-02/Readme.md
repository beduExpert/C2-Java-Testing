## Calculadora de área y perímetro con pruebas parametrizables
### Objetivo
- Aplicar de manera práctica los conocimientos recién adquiridos sobre el framework de pruebas TestNG

#### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. TestNG 7

### Desarrollo
1. Dentro de la carpeta **junitdemo** la misma calculadora de áreas y perímetros empleada en el ejemplo 01, pero esta vez se ha configurado para el empleo de TestNG 7 como framework de pruebas.
2. Se ha generado la clase CalculadoraPerimetroTest, junto con su método para limpieza antes de cada prueba y un data provider para emplearse en la prueba del cálculo del perímetro de un rectángulo.
3. El data provider consiste en un método que va a retornar un Object[][] que contiene los valores de entrada y el resultado esperado para realizar una prueba, y nos permite la reutilización del mismo método con diferentes valores. Para este caso se definen los valores para los lados de un rectángulo y el perímetro resultante en el método **valoresRectangulo**.
4. El método **testRectangulo** está configurado para recibir los datos del data provider, en el orden de los parámetros que éste recibe. La prueba realiza la ejecución de la función y verifica que el resultado obtenido coincida con el esperado.
5. Con ayuda de tu IDE, navegando hasta las clases de prueba y ejecutando los métodos o con el comando `mvn clean test` ejecuta las pruebas y analiza el resultado. ¿Cuál es el valor que causa que la prueba falle? ¿qué cambios serán necesarios para corregir esta prueba?
6. Realiza la corrección en los valores esperados y ejecuta una vez más las pruebas, validando que ahora pasen sin problemas.


