## Ejemplo 02: Simulación de Excepciones con Mockito

### OBJETIVO

- Aprender a simular condiciones donde se lanzan excepciones.

#### REQUISITOS

1. JDK 8o superior.
2. IDE de tu preferencia
3. Apache Maven

#### DESARROLLO

En este ejemplo crearemos algunos métodos que lanzan excepciones dependiendo de los valores que pasamos como argumentos.

Mockito hacce que simular este tipo de condiciones sea muy sencillo.

1. Crea un nuevo proyecto Maven llamado mockito-excepciones en tu IDE.

2. Reemplaza el contenido del archivo pom.xml con el siguiente, en el que se declara que se declara que se usará JUnit 5 como framework de pruebas unitarias, y Mockito como framework de Mocks. 

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>org.bedu.testing</groupId>
	<artifactId>mockito-excepciones</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>Mockito-Excepciones</name>
	<description>Uso de excepciones con Mockito</description>

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
				</plugin>
			</plugins>
		</pluginManagement>
	</build>

</project>
```

3. Crea una interface llamada **Calculadora**, la cual representará un conjunto de operaciones matemáticas que puede realizar nuestra aplicación. La interface debe ser como la siguiente:

```java
public interface Calculadora {
	double suma(double a, double b);

	double resta(double a, double b);

	double multiplica(double a, double b);

	double divide(double a, double b);
}
```

4. Crea una clase AplicacionMatematica que usará la interface anterior para usará la interface anterior para realizar una serie de operaciones matemáticas.

```java
public class AplicacionMatematica {
	private Calculadora calculadora;

	public void setCalculadora(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public double suma(double a, double b) {
		return calculadora.suma(a, b);
	}

	public double resta(double a, double b) {
		return calculadora.resta(a, b);
	}

	public double multiplica(double a, double b) {
		return calculadora.multiplica(a, b);
	}

	public double divide(double a, double b) {
		return calculadora.divide(a, b);
	}
}
```

5. Crea una clase **AplicacionMatematicaTest** que nos ayudará a probar la clase anterior. 

6. Dentro de esta clase, primero crearemos un Mock de la interface Calculadora:

```java
	@Mock
	private Calculadora calculadora;
```

7. Declara una nueva instancia de **AplicacionMatematica** e indica que los mocks deben inyectarse en esta instancia:

```java
	@InjectMocks
	private AplicacionMatematica app;
```

8. Crea un método que lance una excepción, usando el método **doThrow** de Mockito, cuando se invoque alguno de los métodos de la interface *Calculadora* y después verifica que esta excepción se lance usando JUnit 5:

```java
	@Test
	 void sumaNegativosLanzaExcepcion(){
	      Mockito.doThrow(new RuntimeException("Add operation not implemented"))
	         .when(calculadora).suma(-10.0, -20.0);

	      Assertions.assertThrows(RuntimeException.class, () -> {
	    		calculadora.suma(-10.0, -20.0);
	    	}); 
	   }
```

9. ejecuta la prueba y esta deba pasar de manera correcta.
