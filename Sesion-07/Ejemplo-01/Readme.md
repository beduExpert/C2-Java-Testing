## Ejemplo 01: Uso de Mockito en una aplicación sencilla

### Objetivo
- Familiarizarse con el proceso para instalar y usar Mockito en un proyecto de software para realizar pruebas unitarias empleando objetos mock.

### Requisitos
1. JRE y JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven

### Desarrollo
Como hemos visto, Mockito es una herramienta que permite facilitar la creación de objetos mock que cumplan la funcionalidad que requerimos para la realización de pruebas en nuestro software.
Es uno de los framework más utilizados, debido a que cuenta con una gran comunidad en línea que puede ayudar con dudas y problemas que surgen con su uso.

En este ejemplo instalaremos Mockito como una dependencia de Maven y lo emplearemos para generar las pruebas unitarias de una aplicación que lee una lista de nombres desde un archivo de texto.

1. Crearemos un proyecto llamado **ejemploMocks** mediante Maven, y reemplazaremos su archivo **pom.xml** por el siguiente:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>ejemploMocks</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>ejemploMocks</name>

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
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

2. Dentro de la carpeta src/main/java agregaremos la interfaz Lector, que solo contentrá el método leerLinea y su implementación será la siguiente:
```java
package org.bedu;

public interface Lector {
    String leerLinea();
}
```

3. Agregaremos la calse LectorArchivo, que implementará la interfaz Lector de la siguiente manera:
```java
package org.bedu;

import java.io.*;

public class LectorArchivo implements Lector {

    private BufferedReader reader;

    public LectorArchivo(String nombreArchivo) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(nombreArchivo));
    }

    @Override
    public String leerLinea() {
        String leido;
        try {
            leido = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            leido = null;
        }
        return leido;
    }
}
```

4. Tendremos además la clase ExtractorNombres, que será la encargada de leer el archivo y obtener una lista de nombres de él:
```java
package org.bedu;

import java.util.ArrayList;

public class ExtractorNombres {
    private Lector lector;

    public ExtractorNombres(Lector lector) {
        this.lector = lector;
    }

    //Lee una línea de la forma "Pérez López, Juan Fernando" y crea el objeto nombre
    //correspondiente identificando cada campo correctamente.
    public ArrayList<Nombre> leerNombres(){
        ArrayList<Nombre> nombres = new ArrayList<>();

        String linea;
        while((linea = lector.leerLinea()) != null){
            String[] nombreCompleto = linea.split(",");
            String[] apellidos = nombreCompleto[0].split(" ");

            Nombre nombre = new Nombre();
            nombre.setApellidoPaterno(apellidos[0].trim());
            nombre.setApellidoMaterno(apellidos[1].trim());
            nombre.setNombres(nombreCompleto[1].trim());

            nombres.add(nombre);
        }
        return nombres;
    }
}

```

5. El Bean Nombre contendrá los siguientes atributos junto con sus getter y setter:
```java
package org.bedu;

public class Nombre {
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
}
```

6. Ahora agregaremos las pruebas unitarias para ExtractorNombres, agregando la clase ExtractorNombresTest en el paquete org.bedu de la carpeta src/test/java.

7. Debido a que ExtractorNombres depende de un Lector para su funcionamiento, emplearemos Mockito para pasarle un mock de la interfaz requerida. Para ello agregaremos a la clase ExtractorNombres el atributo Lector que llevará la anotación @Mock.
8. Como queremos emplear el mock en la clase ExtractorNombres, en el atributo de la clase de pruebas la anotaremos con @InjectMocks para decirle a Mockito que esa clase es la que utilizará el objeto mock.
9. Como último paso de configuración, dentro agregaremos el método configurar que llevará la anotación @BeforeEach de JUnit para inicializar nuestros objetos, llamando al método initMocks de Mockito.
10. De esta manera, la clase ExtractorNombresTest queda así:
```java
package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorNombresTest {
    @Mock
    private Lector lector;
    @InjectMocks
    private ExtractorNombres extractor;

    @BeforeEach
    void configurar(){
        MockitoAnnotations.initMocks(this);
    }
}
```

11. Agregaremos ahora el método leerNombreCorrectoTest, donde probaremos la funcionalidad de nuestra clase enviando una línea de texto con la estructura que esperamos si leyéramos el archivo real. Le diremos a Mockito qué es lo que debe retornar el método que llama ExtractorNombres y compararemos el resultado esperado con el real:
```java
@Test
    void leerNombreCorrectoTest() {
        //Arrange
        Mockito.when(lector.leerLinea())
                .thenReturn("Pérez López, Juan Fernando")
                .thenReturn(null);
        Nombre esperado = new Nombre();
        esperado.setNombres("Juan Fernando");
        esperado.setApellidoPaterno("Pérez");
        esperado.setApellidoMaterno("López");
        //Actp
        ArrayList<Nombre> resultado = extractor.leerNombres();
        //Assert
        assertTrue(resultado.contains(esperado));
    }
```

12. Sin embargo, esta prueba fallará porque no hemos implementado el método equals del bean Nombre, necesario para usar el método contains de un ArrayList. Agregaremos dicho método de la siguiente manera:
```java
@Override
    public boolean equals(Object obj) {
        if(obj instanceof Nombre){
            Nombre otro = (Nombre)obj;
            return otro.nombres.equals(nombres)
            && otro.apellidoPaterno.equals(apellidoPaterno)
            && otro.apellidoMaterno.equals(apellidoMaterno);
        }
        return false;
    }
```

13. Así hemos realizado las pruebas unitarias de nuestra clase que depende de otra, sin dejar que la dependencia intervenga con el análisis que esperamos de nuestras pruebas.
