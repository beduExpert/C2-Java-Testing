## Ejemplo 02: Refactorizar una clase para poder probarla

### Objetivo
- Basarse en una clase ya implementada y cuyo diseño interfiere con la aplicación de pruebas unitarias para realizar los cambios necesarios para poder hacerle pruebas.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5

### Desarrollo
Cuando desarrollamos software, puede pasar que tengamos que trabajar con módulos o clases previamente desarrollados, los cuales muy probablemente no hayan considerado las pruebas durante su desarrollo.
En este ejemplo nos basaremos en el esqueleto de una aplicación que genera una factura y la envía por correo de la cual queremos probar solo la clase encargada de generar la factura. Para crear la aplicación realizaremos los siguientes pasos:
1. Crearemos un nuevo proyecto de Maven llamado ejemploFacturas, reemplazando el archivo **pom.xml** por el siguiente:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>ejemploFacturas</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>ejemploFacturas</name>

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

2. Agregaremos ahora la clase EnviadorCorreos dentro de src/main/java y en el paquete org.bedu, requerida por la clase GeneradorFacturas. Recordemos que esta no es una implementación completa y solo se incluye como ejemplo:
```java
package org.bedu;

import java.util.logging.Logger;

public class EnviadorCorreos {

    Logger log = Logger.getLogger("EnviadorCorreos");

    public boolean enviarCorreo(String destinatario, String mensaje){
        log.info(String.format("Enviando correo a %s", destinatario));
        log.info(String.format("Cuerpo del correo:%n %s",mensaje));

        log.info("Corre enviado");

        return true;
    }
}
```

3. La implementación de GeneradorFacturas será la siguiente:
```java
package org.bedu;

public class GeneradorFacturas {
    private static final String MSJ_FACTURA = "Estimado %s, por favor pague su factura correspondiente al mes actual por un monto de $%d";

    private EnviadorCorreos correo = new EnviadorCorreos();

    public boolean crearFactura(String destinatario, int cantidad){
        String cuerpoCorreo = String.format(MSJ_FACTURA, destinatario, cantidad);

        return correo.enviarCorreo(destinatario, cuerpoCorreo);
    }
}
```

4. Como podemos observar GeneradorFacturas requiere el uso de EnviadorCorreos, pero resulta poco práctico enviar un correo cada que deseemos probar la funcionalidad de la primera clase. Así, para poder probar independientemente esta clase debemos utilizar una clase falsa que tome el lugar de EnviadorCorreos durante las pruebas. En los siguientes pasos realizaremos este procedimiento.
5. Generaremos una interfaz llamada Enviador, donde expondremos el método enviarCorreo:
```java
package org.bedu;

public interface Enviador {
    boolean enviarCorreo(String destinatario, String mensaje);
}

```

6. Modificaremos la clase EnviadorCorreos para que implemente nuestra nueva interfaz:
```java
package org.bedu;

import java.util.logging.Logger;

public class EnviadorCorreos implements Enviador {

    Logger log = Logger.getLogger("EnviadorCorreos");

    @Override
    public boolean enviarCorreo(String destinatario, String mensaje){
        log.info(String.format("Enviando correo a %s", destinatario));
        log.info(String.format("Cuerpo del correo:%n %s",mensaje));

        log.info("Corre enviado");

        return true;
    }
}
```

7. Modificaremos GeneradorFacturas para que se comunique con la interfaz en lugar de emplear la clase, pero para pasar la implementación correcta deberemos agregar un constructor sobrecargado donde la reciba:
```java
package org.bedu;

public class GeneradorFacturas {
    private static final String MSJ_FACTURA = "Estimado %s, por favor pague su factura correspondiente al mes actual por un monto de $%d";

    private Enviador correo;

    public GeneradorFacturas(){
        correo = new EnviadorCorreos();
    }

    public GeneradorFacturas(Enviador correo){
        this.correo = correo;
    }

    public boolean crearFactura(String destinatario, int cantidad){
        String cuerpoCorreo = String.format(MSJ_FACTURA, destinatario, cantidad);

        return correo.enviarCorreo(destinatario, cuerpoCorreo);
    }
}

```

8. Así podemos proceder al desarrollo de las pruebas unitarias para nuestra clase, pasándole una implementación falsa de EnviadorCorreos que agregaremos en src/test/java:
```java
package org.bedu;

import java.util.logging.Logger;

public class EnviadorCorreosFalso implements Enviador {
    private Logger log = Logger.getLogger("EnviadorCorreosFalso");

    @Override
    public boolean enviarCorreo(String destinatario, String mensaje) {
        log.info(String.format("Fingiendo enviar un correo a %s", destinatario));
        return true;
    }
}
```

9. Ahora crearemos la clase GeneradorFacturasTest, donde se agruparán las pruebas unitarias:
```java
package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorFacturasTest {

    private GeneradorFacturas generador = new GeneradorFacturas();

    @BeforeEach
    void setUp() {
        generador = new GeneradorFacturas(new EnviadorCorreosFalso());
    }

    @Test
    void crearFacturaTest() {
        //Act
        boolean resultado = generador.crearFactura("user@mail.com", 1500);
        //Assert
        assertTrue(resultado);
    }
}
```

10. De esta manera hemos refactorizado una clase que no fue pensada para ser probada, separándola de la otra clase en que depende. Es importante señalar que no siempre se podrá aplicar esta técnica para la refactorización, pues existen casos en los que es poco práctico o imposible extraer una interfaz de la clase de la cual no queremos depender. En este caso puede ser útil crear una subclase que sobreescriba los métodos con los que vayamos a trabajar.
