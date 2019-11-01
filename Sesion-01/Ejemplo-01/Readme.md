##Ejemplo 01: Instalación del ambiente de pruebas

### OBJETIVO

- Instalar y verificar la instalación de un ambiente de pruebas para aplicaciones Java

#### REQUISITOS

1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven

#### DESARROLLO

1. Ve a la perspectiva de Git

![imagen](img/figura_01.png)

![imagen](img/figura_02.png)


2. Selecciona Clonar un repositorio Git (Fig. 03)

![imagen](img/figura_03.png)


3. Ingresa los datos del repositorio (Fig. 04)

![imagen](img/figura_04.png)


4. Valida que la estructura del proyecto sea igual a la siguiente: 

![imagen](img/figura_05.png)


5. Abre el archivo pom.xml y verifica que se agregan las dependencias de JUnit5 (junit jupiter) como framework de pruebas y que se configura el plugin de surefire para ejecutar las pruebas. Para eso, puedes sustituir el contenido del archivo pom.xml del proyecto con el siguiente:

```xml
  <project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.bedu.testing</groupId>
    <artifactId>testing</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>Ejercicio01</name>

    <properties>
      <maven.compiler.source>8</maven.compiler.source>
      <maven.compiler.target>8</maven.compiler.target>
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
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.5.2</version>
      </dependency>
    </dependencies>

    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.2</version>
        </plugin>
      </plugins>
    </build>

  </project>
```

6. Abre la clase SumadorLunarTest, ve al botón RunAs -> JUnit Test

![imagen](img/figura_07.png)


7. Verifica el resultado de la prueba, el cual debe ser una línea roja como la que se muestra en la siguiente imagen:

![imagen](img/figura_08.png)
