## Reto 02: Refactorizando una clase distinta


### Objetivo
Aplicar el proceso visto en el ejemplo 02 a una aplicación distinta para practicar la adición de pruebas unitarias a código que no las contemplaba en un inicio.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5

### Desarrollo
Contamos con una aplicación que lee un archivo de texto del disco duro que contiene una lista de palabras separadas por coma, las cuales son extraídas y puestas en un ArrayList de String.
El reto consiste en realizar las pruebas de la funcionalidad de la clase SeparadorPalabras separando su funcionalidad de la clase LectorArchivo. Los pasos para crear la aplicación son los siguientes:
1. Crearemos un proyecto Maven llamado lectorPalabras, cuyo **pom.xml** tendrá la siguiente estructura:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>lectorPalabras</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>lectorPalabras</name>

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
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```
2. Agregaremos la clase LectorArchivos en src/main/java:
```java
package org.bedu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorArchivos {
    public String leerArchivo(String nombreArchivo) throws IOException {
        byte[] archivo = Files.readAllBytes(Paths.get(nombreArchivo));
        return new String(archivo);
    }
}

```

3. Y finalmente, la clase SeparadorPalabras:
```java
package org.bedu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SeparadorPalabras {
    private LectorArchivos lector = new LectorArchivos();

    public List<String> leerPalabras(String nombreArchivo) throws IOException {
        String contenidoArchivo = lector.leerArchivo(nombreArchivo);
        String[] palabras = contenidoArchivo.split(",");
        return Arrays.asList(palabras);
    }
}
```

<details>
	<summary>Solución</summary>
    1. El primer paso para refactorizar es extraer una interface que exponga los métodos de LectorArchivos. Dicha interfaz quedará de la siguiente manera:
    ```java
    package org.bedu;

import java.io.IOException;

public interface Lector {
    String leerArchivo(String nombreArchivo) throws IOException;
}
    ```

    2. Haremos que LectorArchivos implemente la interfaz Lector:
    ```java
    package org.bedu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorArchivos implements Lector {
    @Override
    public String leerArchivo(String nombreArchivo) throws IOException {
        byte[] archivo = Files.readAllBytes(Paths.get(nombreArchivo));
        return new String(archivo);
    }
}
    ```

    3. Agregaremos un constructor a SeparadorPalabras para recibir la implementación de Lector:
    ```java
    package org.bedu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SeparadorPalabras {
    private Lector lector;

    public SeparadorPalabras(){
        lector = new LectorArchivos();
    }

    public SeparadorPalabras(Lector lector) {
        this.lector = lector;
    }

    public List<String> leerPalabras(String nombreArchivo) throws IOException {
        String contenidoArchivo = lector.leerArchivo(nombreArchivo);
        String[] palabras = contenidoArchivo.split(",");
        return Arrays.asList(palabras);
    }
}
    ```

    4. Crearemos la clase falsa que implemente Lector en src/test/java:
    ```java
    package org.bedu;

import java.io.IOException;

public class LectorArchivosFalso implements Lector {
    @Override
    public String leerArchivo(String nombreArchivo) throws IOException {
        return "palabras,separadas,por,coma";
    }
}
    ```

    5. Finalmente podremos añadir nuestros casos de prueba en la clase SeparadorPalabrasTest:
    ```java
    package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeparadorPalabrasTest {

    private SeparadorPalabras separador;
    @BeforeEach
    void setUp() {
        separador = new SeparadorPalabras(new LectorArchivosFalso());
    }

    @Test
    void leerPalabrasTest() throws IOException {
        //Arrange
        int numPalabras = 4;
        //Act
        List<String> palabras = separador.leerPalabras("archivo.txt");
        //Assert
        assertEquals(numPalabras, palabras.size());
    }
}
    ```
</details>
