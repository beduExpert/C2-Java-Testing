## Ejemplo 03: Timeouts

### OBJETIVO

- Aprender a validar condiciones cuando la ejecución de im método toma más tiempo del esperado.

#### REQUISITOS

1. JDK 8 o superior.
2. IDE de tu preferencia
3. Apache Maven

#### DESARROLLO

En este ejemplo validaremos que la ejecución de un método sea menor a un timeot indicado en milisengundos.

1. Crea un nuevo proyecto Maven llamado mockito-timeout en tu IDE.

2. Reemplaza el contenido del archivo pom.xml con el siguiente, en el que se declara que se declara que se usará JUnit 5 como framework de pruebas unitarias, y Mockito como framework de Mocks. 

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>org.bedu.testing</groupId>
	<artifactId>mockito-timeout</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>Mockito-Timeot</name>
	<description>Uso de timeout con Mockito</description>

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

8. Crea un método que verificque que varias invocaciones métodos de la interface *Calculadora* se ejecuten cada uno en menos de 100 milisegundos:

```java
	@Test
	void sumaYRestaMenos100Milisegundos() {

		when(calculadora.suma(20.0, 10.0)).thenReturn(30.0);

		when(calculadora.resta(20.0, 10.0)).thenReturn(10.0);


		Assertions.assertEquals(app.resta(20.0, 10.0), 10.0, 0);

		Assertions.assertEquals(app.suma(20.0, 10.0), 30.0, 0);

		verify(calculadora, Mockito.timeout(100)).suma(20.0, 10.0);

		verify(calculadora, Mockito.timeout(100).times(1)).resta(20.0, 10.0);
	}
```

El método Mockito.verify se encarga de validar si se cumple alguna condición, en este caso que el método se invoque en menos de 100 milisengundos. Esto lo indicamos con el método **Mockito.timeout".

9. Ejecuta la prueba y esta deba pasar de manera correcta.
