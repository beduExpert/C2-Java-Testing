## Postwork 03: TDD en tu proyecto personal

### OBJETIVO

- Implementar TDD en un conjunto de clases que comprendan un proyecto más grande, lo que permitirá usar y ver algunas técnicas más especializadas de TDD.
- Programar y ejecutar un conjunto de pruebas unitarias para una clase de lógica de negocio.

#### REQUISITOS

1. Descargar el proyecto de ejemplo que se encuentra en el repositorio.
2. Tener conexión a Internet para poder descargar las dependencias del proyecto

#### DESARROLLO

1. Descarga el proyecto de ejemplo que se encuentra en la carpeta **proyecto** de esta sesión.

2. El proyecto ya se encuentra configurado para hacer uso de JUnit 5 como framework de pruebas y está listo para poder usarse. Abre el archivo **pom.xml** y revisa que se encuentren las siguientes dependencias:

```xml
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
```

2. Dentro del proyecto, existen los siguientes paquetes.

 - *exception.email*: Contiene las clases de excepciones que pueden lanzarse con enviar un correo electrónico.
 - *modelo*: Contiene las clases que actuan como modelo de datos de la aplicación.
 - *persistencia*: Contiene las interfaces para el manejo de información en base de datos.
 - *negocio*: Contiene las clases que implementan la lógica de negocio.
 
3. En esta sesión trabajaremos principalmente con las clases del negocio, en particular con la clase ***AsignadorDeCastigos***. Abre esta clase y revisa el contenido. A modo de resumen, contiene los siguientes métodos:

 - cuentaCastigosDisponibles
 - leerPropiedades
 - recortarLista
 - asignarCastigos
 - desordenar
 - ordenarCastigosPorPrioridad
 - ordenarCastigosPorPrecio
 - ordenarCastigosPorPrecio
 - ponerCastigos
 
4. En el directorio de pruebas */src/test/java* se encuentra la clase AsignadorDeCastigosTest, la cual rellenaremos con sus correspondientes métodos de prueba.

```java
public class AsignadorDeCastigosTest {

}
```

5. Declara una instancia de la clase **AsignadorDeCastigos** que es la clase que estaremos validando. 

```java
private AsignadorDeCastigos adc;

```

6. Crea un método llamado **llenarBaseDeDatos** decorado con la anotación **@BeforeEach**. En este método inicializaremos una serie de listas de datos que nos ayudarán en cada una de las pruebas. Al estar anotado este étodo con *BeforeEach* aseguramos que estos valores se inicializarán antes de ejecutar cada método de prueba.

```java

@BeforeEach
	public void llenarBaseDeDatos() throws ParseException {
		Castigo castigo1 = new Castigo("Garrafón", false, 20, 1);
		Castigo castigo2 = new Castigo("Maruchan", false, 80, 0);
		Castigo castigo3 = new Castigo("Pagar Cena de todos", false, 1200, -1);
		Castigo castigo4 = new Castigo("Cervezas", false, 100, 0);
		Castigo castigo5 = new Castigo("Chocolates", false, 70, 0);
		Castigo castigo6 = new Castigo("Café", false, 40, 0);
		Castigo castigo7 = new Castigo("Vasos", false, 28, 0);
		Usuario usuario1 = new Usuario("algo1@serv.com", "hola", "hola");
		Usuario usuario2 = new Usuario("algo2@serv.com", "hola", "hola");
		Usuario usuario3 = new Usuario("algo3@serv.com", "hola", "hola");
		Usuario usuario4 = new Usuario("algo4@serv.com", "hola", "hola");
		Usuario usuario5 = new Usuario("algo5@serv.com", "hola", "hola");
		Usuario usuario6 = new Usuario("algo6@serv.com", "hola", "hola");

		List<Castigo> listaCastigos = new ArrayList<Castigo>();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		listaCastigos.add(castigo1);
		listaCastigos.add(castigo2);
		listaCastigos.add(castigo3);
		listaCastigos.add(castigo4);
		listaCastigos.add(castigo5);
		listaCastigos.add(castigo6);
		listaCastigos.add(castigo7);

		usuario1.setHistorial(generarHistorial(30));
		usuario2.setHistorial(generarHistorial(30));
		usuario3.setHistorial(generarHistorialFijo(30, 15));
		usuario4.setHistorial(generarHistorial(30));
		usuario5.setHistorial(generarHistorialFijo(30, 20));
		usuario6.setHistorial(generarHistorialFijo(30, 25));

		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);
		listaUsuarios.add(usuario3);
		listaUsuarios.add(usuario4);
		listaUsuarios.add(usuario5);
		listaUsuarios.add(usuario6);

		adc = new AsignadorDeCastigos(listaCastigos, listaUsuarios);
	}

```

7. Agrega métodos de prueba para comprobar el funcionamiento de la clase. Se muestran algunas sugerencias pero puedes agregar mucho más métodos de prueba:

```java
	@Test
	public void pruebaLeeCorrectamenteElArchivoDePropiedades() {
		assertEquals(3, adc.getNumeroDeCastigados());
		assertEquals("Fri", adc.getDiaInicio());
	}

	@Test
	public void pruebaActivaCastigos() {
		List<Castigo> lista = adc.getListaCastigos();
		adc.asignarCastigos();
		int cuenta = 0;

		for (Castigo c : lista) {
			cuenta = c.isDisponible() ? cuenta + 1 : cuenta;
		}
		// Se piden 4. 7 menos los 3 asignados
		assertEquals(4, cuenta);
	}

	@Test
	public void pruebaRecortaLista() {
		adc.asignarCastigos();
		assertEquals(3, adc.getListaCastigos().size());
	}

	@Test
	public void pruebaDesordenaListas() {
		adc.desordenar();
		List<Castigo> lista1 = new ArrayList<Castigo>(adc.getListaCastigos());
		adc.desordenar();
		List<Castigo> lista2 = new ArrayList<Castigo>(adc.getListaCastigos());

		assertEquals(true, !lista1.equals(lista2));
	}

	@Test
	public void pruebaAsignaPorPrioridad() {
		List<Castigo> lista = adc.getListaCastigos();

		String cadena = "";

		for (Castigo c : lista) {
			cadena += c.getDescripcion();
		}

		assertEquals(true, cadena.contains("Garrafón"));
	}

	@Test
	public void pruebaOrdenPrecio() {
		adc.asignarCastigos();

		List<Castigo> lista = adc.getListaCastigos();

		float precio1 = lista.get(0).getPrecio();
		float precio2 = lista.get(1).getPrecio();
		float precio3 = lista.get(2).getPrecio();

		assertEquals(true, precio1 >= precio2 && precio2 >= precio3);
	}

	@Test
	public void pruebaOrdenaUsuariosPorRetrazos() {
		adc.asignarCastigos();
		List<Usuario> lista = adc.getListaCastigados();

		assertEquals("algo6@serv.com", lista.get(0).getEmail());
		assertEquals("algo5@serv.com", lista.get(1).getEmail());
		assertEquals("algo3@serv.com", lista.get(2).getEmail());
	}

	@Test
	public void pruebaAsignaCastigosPorPrecio() {
		adc.asignarCastigos();
		List<Usuario> lista = adc.getListaCastigados();

		float precio1 = lista.get(0).getCastigo().getPrecio();
		float precio2 = lista.get(1).getCastigo().getPrecio();
		float precio3 = lista.get(2).getCastigo().getPrecio();

		assertEquals(true, precio1 >= precio2 && precio2 >= precio3);
	}

	private List<Asistencia> generarHistorial(int dias) throws ParseException {
		Random generador = new Random();

		return generarHistorialFijo(dias, generador.nextInt(10));
	}

	private List<Asistencia> generarHistorialFijo(int dias, int retardo) throws ParseException {
		List<Asistencia> lista = new ArrayList<Asistencia>();
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, 2011);
		calendario.set(Calendar.MONTH, 1);

//        String mesYear = "/01/2011";
//        String fecha;

		for (int i = 0; i < dias; i++) {
//            fecha = (1 + i) + mesYear;
//            lista.add(new Asistencia(DateFormat.getDateInstance().parse(fecha), false, retardo));
			calendario.set(Calendar.DAY_OF_MONTH, (1 + i));
			lista.add(new Asistencia(calendario.getTime(), false, retardo));
		}

		return lista;
	}
        
```

8. Finalmente, ejecuta la prueba, esta debe pasar de manera correcta.


