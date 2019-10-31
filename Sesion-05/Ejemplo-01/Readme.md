## Ejemplo 01: Calculadora con palabras

### Objetivo
- Diseñar pruebas unitarias para una clase que ya ha sido diseñada y es funcional, para comprobar que los resultados que ésta da sean correctos.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5

### Desarrollo
En este ejemplo, desarrollaremos una calculadora que reciba mediante un parámetro String el nombre de la operación que va a realizar entre 4 diferentes (suma, resta, multiplicación y división) y los valores necesarios para realizar la operación, retornando el resultado de ésta. Para ello realizaremos los siguientes pasos:

1. Crearemos un proyecto nuevo de Maven, al cual nombraremos **calculadora**.
2. Reemplazaremos el contenido del archivo **pom.xml** con el código siguiente, que se encargará de configurar nuestro proyecto para usar JUnit 5 como framework de pruebas:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>calculadora</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>calculadora</name>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.5.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

```

3. En la carpeta src/main/java y dentro del paquete org.bedu crearemos la clase **Calculadora**, cuyo código contendrá la implementación de las 4 operaciones de la calculadora, así como el método calcular que será el que reciba el nombre de la operación a realizar:
```java
package org.bedu;

public class Calculadora {
    private Double sumar(Double a, Double b){
        return a + b;
    }

    private Double restar(Double a, Double b){
        return a - b;
    }

    private Double multiplicar(Double a, Double b){
        return a * b;
    }

    private Double dividir(Double a, Double b){
        return a / b;
    }

    public Double calcular(String operacion, Double a, Double b){
        switch (operacion){
            case "suma":
                return sumar(a, b);
            case "restar":
                return restar(a, b);
            case "multiplicar":
                return multiplicar(a, b);
            case "dividir":
                return dividir(a, b);
        }
        return 0.0;
    }
}

```

4. Ahora, agregaremos la clase que contendrá las pruebas unitarias de nuestra aplicación. Para ello, dentro de la carpeta src/test/java y en el paquete org.bedu crearemos una clase llamada **CalculadoraTest**, que contiene un método de setup antes de cada prueba para iniciar con una instancia nueva y asegurar la independencia de cada prueba, así como el primer caso de prueba para el cálculo de la suma. En los comentarios se identifica también cada uno de los pasos de la prueba:
```java
package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        //Arrange (se incluye en este método para no repetirlo en cada caso de prueba
        calc = new Calculadora();
    }

    @Test
    void calcularSumaTest() {
        //Arrange
        Double esperado = 4.0;
        //Act
        Double resultado = calc.calcular("sumar", 2.0, 2.0);
        //Assert
        assertEquals(esperado, resultado);
    }
}
```

5. Las pruebas se pueden ejecutar mediante el IDE, o desde la consola ubicándose en la raíz del proyecto y ejecutando el comando `mvn clean test`. Se nos presentará el resumen de las pruebas ejecutadas y el resultado obtenido de una manera similar a la siguiente:
```
[INFO] -------------------------------------------------------                                                          [INFO]  T E S T S                                                                                                       [INFO] -------------------------------------------------------                                                          [INFO] Running org.bedu.CalculadoraTest                                                                                 [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.007 s - in org.bedu.CalculadoraTest            [INFO]                                                                                                                  [INFO] Results:                                                                                                         [INFO]                                                                                                                  [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0                                                                 [INFO]                                                                                                                  [INFO] ------------------------------------------------------------------------                                         [INFO] BUILD SUCCESS                                                                                                    [INFO] ------------------------------------------------------------------------                                         [INFO] Total time:  5.475 s                                                                                             [INFO] Finished at: 2019-10-30T19:01:57-06:00                                                                           [INFO] ------------------------------------------------------------------------
```

6. De esta manera hemos implementado el primer caso de prueba para las pruebas unitarias de una clase.
