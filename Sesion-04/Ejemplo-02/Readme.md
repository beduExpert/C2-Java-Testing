## Calculadora de área y perímetro con pruebas parametrizables
### Objetivo
- Aplicar de manera práctica los conocimientos recién adquiridos sobre el framework de pruebas TestNG

#### Requisitos
1. JDK 8 o superiór
2. IDE de tu preferencia
3. Apache Maven
4. TestNG 7

### Desarrollo

1. Crea un nuevo proyecto Maven en el IDE de tu preferencia.

2. Reemplaza el archivo pom.xml con el siguiente, en el cual se configura para el empleo de **TestNG 7** como framework de pruebas.

```java
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>testngdemo</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>testngdemo</name>
  <description>Proyecto para la creación de pruebas parametrizadas con TestNG</description>
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
    <plugins>
    </plugins>
  </build>
</project>
```

3. Crea una clase llamada ***CalculadoraArea*** dentro del código productivo del proyecto (**src/main/java**) y dentro de este coloca el siguiente código:

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

4. Crea en este mismo paquete una clase llamada **CalculadoraPerimetro** y coloca el siguiente código:
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

Estas dos clases representarán el código productivo al cual debemos agregarle las pruebas. 

5. Crea una clase llamada ***CalculadoraPerimetroTest*** en el paquete de pruebas de Maven (***src/test/java***).

6. Dentro de esa clase agrega una variable de instancia de tipo **CalculadoraPerimetro**, esta será la variable que usaremos en nuestras pruebas. 

```java
CalculadoraPerimetro calc;
```

7. Agrega un método para limpieza antes de cada prueba, este método asegurará que el resultado de una prueba no interfiere con el resultado de otra logrando así un nivel de aislamiento. Este método debe estar decorado con la anotación **@BeforeTest**, la cual indica que ese método debe invocarse antes de cualquier otro método de prueba:

```java
@BeforeTest
public void cleanup(){
  calc = new CalculadoraPerimetro();
}
```

8. Agrega un *data provider* para emplearse en la prueba del cálculo del perímetro de un rectángulo. Este data provider consistirá en un arreglo doble, en el que cada arreglo contendrá tres valores: la base, la altura y el perímetro esperado. Decora el data provider con la anotación ***@DataProvider*** y especifica un nombre a través de su atributo *name*:
```java
@DataProvider(name = "perimetroRectangulo")
    public Object[][] valoresRectangulo(){
        return new Object[][]{
                {2.0, 2.0, 8.0},
                {5.0, 4.0, 18.0},
                {1.0, 10.0, 22.0},
                {8.0, 25.0, 66.0},
                {2.0, 9.0, 81.0}
        };
    }
```
El data provider consiste en un método que va a retornar un Object[][] que contiene los valores de entrada y el resultado esperado para realizar una prueba, y nos permite la reutilización del mismo método con diferentes valores. Para este caso se definen los valores para los lados de un rectángulo y el perímetro resultante en el método **valoresRectangulo**.

9. Crea el método **testRectangulo** el cual debe estar configurado para recibir los datos del data provider, en el orden de los parámetros que éste recibe. La prueba realizará la ejecución de la función y verifica que el resultado obtenido coincida con el esperado.

```java
    @Test(dataProvider = "perimetroRectangulo")
    public void testRectangulo(Double base, Double altura, Double resultadoEsperado) {
        Double real = calc.rectangulo(base, altura);
        assertEquals(real, resultadoEsperado);
    }
```

10. Con ayuda de tu IDE, navegando hasta las clases de prueba y ejecutando los métodos o con el comando `mvn clean test` ejecuta las pruebas y analiza el resultado. ¿Cuál es el valor que causa que la prueba falle? ¿qué cambios serán necesarios para corregir esta prueba?

11. Realiza la corrección en los valores esperados y ejecuta una vez más las pruebas, validando que ahora pasen sin problemas.
