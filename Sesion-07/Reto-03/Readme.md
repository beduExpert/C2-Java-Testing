## Reto 03: Creación de mocks para suplantar una conexión a una base de datos

### Objetivo
- Ayudarse de Mockito para la creación de mocks que ayuden a generar pruebas unitarias para una clase que depende de información de una base de datos.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. Mockito

### Desarrollo
Durante el desarrollo de aplicaciones que dependan de información de bases de datos, es complicado contar siempre con un ambiente donde sea posible estar modificando la información de dicha base de datos o estar realizando diferentes conexiones para realizar pruebas. Es por esto que durante este reto te ayudarás de Mockito para la realización de las pruebas de la clase PagosService, que depende de un repositorio y se conecta a una base de datos para leer información.

Dentro de la carpeta **retoBaseDatos** se encuentra un proyecto desarrollado empleando el framework Spring, para facilitar la conexión a una base de datos mediante una interfaz llamada PagosRepository, es esta clase de la que tendrás que generar un mock para usar en las pruebas.
Esta aplicación ya cuenta con la configuración necesaria para emplear JUnit 5 y Mockito como frameworks para pruebas.


<details>
	<summary>Solución</summary>

1. El primer paso consiste en crear la clase PagosServiceTest dentro de `src/test/java` en el paquete org.bedu.retoBaseDatos.service para crear las pruebas unitarias del servicio.

2. Una vez que hayamos creado la clase, le agregaremos los atributos necesarios para la prueba: una instancia de PagosService y una de PagosRepository, junto con sus correspondientes anotaciones para indicarle a Mockito dónde actuar:

```java
     @Mock
    private PagosRepository repositorio;
    @InjectMocks
    private PagosService servicio;
```

3. Agregaremos también un método setUp para configurar nuestro servicio y el objeto mock antes de cada prueba:

```java
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
```

4. Ahora, toca el turno de agregar cada uno de los métodos de prueba, recordando probar la funcionalidad de los métodos públicos del servicio. De esa forma, los métodos de prueba quedan:

```java
    @Mock
    private PagosRepository repositorio;
    @InjectMocks
    private PagosService servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void buscarTodosTest() {
        Mockito.when(repositorio.findAll()).thenReturn(crearListaPagos());

        Iterable<Pago> resultado = servicio.buscarTodos();
        Pago primerElemento = resultado.iterator().next();

        assertEquals(1, primerElemento.getId());
    }

    @Test
    void buscarPorIdTest() {
        Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(crearPago()));
        Pago resultado = servicio.buscarPorId(1);
        assertEquals(1, resultado.getId());
    }

    @Test
    void editarTest() {
        Mockito.when(repositorio.save(Mockito.any(Pago.class))).then(AdditionalAnswers.returnsFirstArg());
        Pago editado = crearPago();
        editado.setCantidad(6000);
        Pago resultado = servicio.editar(editado);
        assertEquals(editado, resultado);
    }

    @Test
    void eliminarTest() {
        servicio.eliminar(1);
        Mockito.verify(repositorio, Mockito.times(1)).deleteById(1);
    }

    private Pago crearPago(){
        Pago pago = new Pago();
        pago.setId(1);
        pago.setCantidad(3500);
        pago.setConcepto("Servicio de mantenimiento de PC");
        pago.setFecha(LocalDate.now());
        return pago;
    }

    private List<Pago> crearListaPagos(){
        return Collections.singletonList(crearPago());
    }
```

5. De esta forma hemos creado un servicio que depende de una conexión a una base de datos y hemos realizado pruebas de su funcionalidad sin depender de una conexión real ni de trabajar con información completa.

</details>
