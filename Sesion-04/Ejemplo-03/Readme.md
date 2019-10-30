## Factorial de un número

### Objetivo
- Generar una sencilla suite de pruebas con la generación de un archivo testng.xml.

### Requisitos
1. JDK 8 o más reciente
2. IDE de tu preferencia
3. Apache Maven
4. TestNG

### Desarrollo
El factorial de un número se define como el producto de multiplicar éste por cada uno de los números que le preceden, hasta llegar a 1. De esta manera, el factorial de 4 es 24 (4×3×2×1 = 24); sin embargo, existen un par de reglas ya definidas para el factorial:
- El factorial de 0 es 1
- No existe el factorial de los números negativos.

Durante este ejemplo, desarrollaremos una suite de pruebas que permitirá probar los casos válidos para la función factorial.

1. Mediante el IDE de tu preferencia, crea un nuevo proyecto de Maven, al cual llamaremos **factorial**.
2. Reemplazar el contenido de **pom.xml** con lo siguiente, esto configurará el proyecto para usar TestNG como framework de pruebas:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.bedu</groupId>
  <artifactId>factorial</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>factorial</name>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>7.0.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <pluginManagement>
      <plugins>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

3. Agregaremos la clase Factorial dentro de src/main/java en el paquete org.bedu. Esta clase tendrá la siguiente estructura:
```java
package org.bedu;

public class Factorial {
    public long factorial(long x){
        long acumulado = 1;
        for(; x > 1; x--){
            acumulado*= x;
        }
        return acumulado;
    }
}
```

4. Ahora agregaremos un par casos de prueba que comprobará si el resultado que obtenemos es correcto, para ello, dentro de src/test/java agregaremos la clase FactorialTest, en el paquete org.bedu
```java
package org.bedu;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {
    Factorial factorial;
    @BeforeTest
    public void cleanup(){
        factorial = new Factorial();
    }
    @Test
    public void testFactorialCero() {
        long resultado = factorial.calcular(0);
        assertEquals(resultado, 1);
    }
    @Test
    public void testFactorialDiez() {
        long resultado = factorial.calcular(10);
        assertEquals(resultado, 3628800);
    }
}
```

5. Para crear una suite de pruebas, agregaremos un archivo llamado testng.xml a la raíz del proyecto. En este archivo nombraremos a la suite de pruebas y especificaremos que las pruebas se ejecuten en paralelo, sin importar el orden en que éstas sean ejecutadas. Incluiremos los métodos testFactorialDiez y testFactorialCero de la clase FactorialTest dentro de la suite **Pruebas correctas**
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Pruebas correctas" verbose="1" >
<test name="Valores válidos" preserve-order="false" parallel="true">
    <classes>
        <class name="org.bedu.FactorialTest" />
    </classes>
</test>
</suite>
```

6. Mediante el IDE, o con el comando `mvn clean test` podremos ejecutar el archivo **testng.xml** con nuestra suite de pruebas.
