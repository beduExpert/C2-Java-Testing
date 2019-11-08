## Reto 01: Uso básico de Mockito

### Objetivo
- Emplear Mockito para crear simples objetos falsos que permitirán realizar pruebas unitarias de una clase que depende de la implementación de otra.

### Requisitos
1. JRE y JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven

### Desarrollo
Como vimos en el ejemplo 1, Mockito permite controlar los valores que entran a los métodos que vamos a probar de nuestras clases, fingiendo ser la interfaz que necesitamos y proveyendo los valores que requerimos.

Durante este reto, tendrás que desarrollar un multiplicador para números enteros positivos que se base en una implementación externa de un sumador y realizar las pruebas para la clase multiplicador sin incluir la referencia al sumador.

1. El primer paso consiste en crear un proyecto de Maven como se ha visto anteriormente e incluir las dependencias necesarias para el uso de JUnit y Mockito.
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>retoMultiplicador</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>retoMultiplicador</name>

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
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.4.2</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>3.1.0</version>
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

2. Después, añadiremos la implementación de la clase Multiplicador:
```java
package org.bedu;

public class Multiplicador {
    private Sumador sumador;

    public int multiplicar(int x, int y){
        int resultado = 0;
        for (; y > 0; y--) {
            resultado = sumador.sumar(resultado, x);
        }
        return resultado;
    }
}
```

3. La interfaz Sumador será la siguiente:
```java
package org.bedu;

public interface Sumador {
    int sumar(int x, int y);
}
```

4. Y la clase SumadorSimple:
```java
package org.bedu;

public class SumadorSimple implements Sumador {
    @Override
    public int sumar(int x, int y) {
        return x + y;
    }
}
```

5. Con esto, ya se tiene la aplicación lista para ser probada. A partir de aquí tu trabajo es crear la clase MultiplicadorTest y agregar diferentes casos de prueba.

<details>
	<summary>Solución</summary>
    1. Para este reto, se tendrá que crear la clase MultiplicadorTest en src/test/java, agregarle las propiedades Sumador y Multiplicador y anotarlos con @Mock e @InjectMocks, respectivamente. Además de indicarle a Mockito que configure nuestros objetos para la prueba.
    2. Para esta solución se muestran los casos de prueba para verificar multiplicando 2x2 y 4x5, quedando la clase completa:
```java
package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicadorTest {
    @Mock
    private Sumador sumador;
    @InjectMocks
    private Multiplicador mult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void multiplicar2y2Test() {
        Mockito.when(sumador.sumar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(4);
        int esperado = 4;

        int resultado = mult.multiplicar(2, 2);

        assertEquals(esperado, resultado);
    }

    @Test
    void multiplicar4y5Test() {
        Mockito.when(sumador.sumar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(20);
        int esperado = 20;

        int resultado = mult.multiplicar(5, 4);

        assertEquals(esperado, resultado);
    }
}
```
<details>
