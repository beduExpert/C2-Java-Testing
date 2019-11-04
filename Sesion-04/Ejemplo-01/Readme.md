## Ejemplo 01: Calculadora de área y perímetro
### Objetivo
- Aplicar de manera práctica los conocimientos recién adquiridos sobre el framework de pruebas JUnit

#### Requisitos
1. JDK 8 o superiór
2. IDE de tu preferencia
3. Apache Maven
4. JUnit 5

### Desarrollo

En este ejercicio haremos una aplicación demo que caculará el área y el perímetro de una serie de figuras geométricas: cuadrado, rectángulo, triángulo y círculo. Y aplicaremos una serie de pruebas unitarias con JUnit 5 para comprobar que la aplicación funciona de forma correcta.

1. Crea un nuevo proyecto Maven en Eclipse llamado **junitdemo**:

2. Reemplaza el archivo **pom.xml** de proyecto con el siguiente, en el que lo configuramos para usar JUnit5 como su framework de pruebas:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>junitdemo</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>junitdemo</name>
  <description>Proyecto para la creación de pruebas unitarias con JUnit 5</description>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
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
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.1</version>
      </plugin>
    </plugins>
  </build>
</project>

```
3. Dentro del proyecto crea una clase llamada ***CalculadoraArea*** dentro de la sección de código productivo de Maven (***src/main/java***) y coloca el siguiente contenido dentro de la clase, el cual nos permite calcular el área de cada una de las figuras geométricas:

```java
public class CalculadoraArea {

    public double cuadrado(double lado){
        return lado * lado;
    }

    public double rectangulo(double base, double altura){
        return base * altura;
    }

    public double triangulo(double base, double altura){
        return base * altura / 2;
    }

    public double circulo(double radio){
        return Math.PI * Math.pow(radio, 2);
    }
}

```

4. Dentro de la misma sección del proyecto crea una clase llamada ***CalculadoraPerimetro*** y coloca el siguiente código, el cual permite calcular el área de cada uno de los tipos de figuras:

```java
public class CalculadoraPerimetro {

    public double cuadrado(double lado){
        return 4 * lado;
    }

    public double rectangulo(double base, double altura){
        return (2 * base) + (2 * altura);
    }

    public double triangulo(double lado1, double lado2, double lado3){
        return lado1 + lado2 + lado3;
    }

    public double circulo(double diametro){
        return Math.PI * diametro;
    }
}
```

Con esto ya tenemos el código productivo de la aplicación, y pasaremos a crear el código de las pruebas.

5. Crea una nueva clase llamada ***CalculadoraAreaTest*** en la sección de código de pruebas de Maven (***src/test/java***). Por convención, en JUnit y otros frameworks de pruebas, el nombre de la clase de prueba debe ser igual al nombre de la clase que contiene el código que probaremos + el posfijo *Test*. Eso quiere decir que la clase *CalculadoraAreaTest* tendrá el código de pruebas de *CalculadoraArea*. Esta es una forma sencilla de organizar el código y encontrar rápidamente las clases correspondientes.

6. Dentro de la clase ***CalculadoraAreaTest*** agrega el siguiente código:

```java
CalculadoraArea calc;
    
    @BeforeEach
    public void cleanup(){
        calc = new CalculadoraArea();
    }
```

en él creamos una variable de instancia llamada *calc* de tipo ***CalculadoraArea*** la cual usaremos posteriormente para ejecutar los métodos que vamos a probar. 

Posteriormente, hay un método llamado **cleanup** el cual está decorado con la anotación **@BeforeEach**. Esta anotación ayuda a indicar que un método debe ser llamado antes de ejecutar cada una de las pruebas contenidas en la clase. En este caso lo que haremos será crear una nueva instancia de **CalculadoraArea** antes de la ejecución de cada prueba, asegurando que las pruebas anteriores no afecten el resultado de la actual.

7. Ahora, agrega el siguiente método de prueba:

```java
  @Test
  public void rectanguloTest(){
        double resultado = calc.rectangulo(2, 4);
        assertEquals(10, resultado);
  }
```

Este método se encarga de probar que la función que calcula el área del rectángulo funciona de manera correcta. El método está decorado con la anotación **@Test** la cual le indica al framework que este es un método que debe ejecutar durante las pruebas. 

8. Crea una nueva clase de prueba llamada ***CalculadoraPerimetroTest*** y coloca el siguiente código dentro de ella:

```java
class CalculadoraPerimetroTest {

    CalculadoraPerimetro calc;

    @BeforeEach
    public void cleanup(){
        calc = new CalculadoraPerimetro();
    }
    @Test
    public void rectanguloTest(){
        double resultado = calc.rectangulo(2, 4);
        assertEquals(15, resultado);
    }
}
```
Esta clase calcula el perímetro del rectángulo. La explicación de los métodos de la clase es la misma que para la clase anterior.

9. Los métodos de prueba se encuentran en las clases **CalculadoraPerimetroTest** y **CalculadoraAreaTest** y están anotados con **@Test** para indicarle a JUnit que esos métodos son para realizar la prueba de alguna funcionalidad, sin embargo, su implementación es incorrecta y causan que la prueba falle cuando no debería.

Con ayuda de tu IDE, navegando hasta las clases de prueba y ejecutando los métodos o con el comando `mvn clean test` ejecuta las pruebas y analiza el resultado. ¿Cuál es el error en la implementación?

10. Realiza la corrección en los valores esperados y ejecuta una vez más las pruebas, validando que ahora pasen sin problemas.

De esta manera se ha realizado una prueba simple prueba unitaria empleando JUnit 5.

