## Reto 02: Creación de mocks para suplantar código que no existe

### Objetivo
- Ayudarse de Mockito para la creación de mocks para probar una clase que depende de una interfaz cuya implementación aún no se encuentra disponible.

### Requisitos
1. JDK 8 o superior
2. IDE de tu preferencia
3. Apache Maven
4. Mockito

### Desarrollo
Además de permitir realizar pruebas unitarias para aislar la funcionalidad de una clase de sus dependencias, Mockito también ayuda a realizar pruebas cuando dependemos de interfaces que aún no están implementadas o cuya implementación aún no es accesible por nosotros.

Este reto consistirá en usar Mockito para suplantar una dependencia de la que solo contamos con la interfaz. Para ello:

1. Crearemos un nuevo proyecto de Maven llamado **retoMocks**, reemplazando su archivo **pom.xml** con el siguiente:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.bedu</groupId>
  <artifactId>retoMocks</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>retoMocks</name>

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

2. Agregaremos una interfaz llamada ConectorHttp en src/main/java que describirá la funcionalidad para conectarse a una API REST y recuperar una lista de usuarios, ver el detalle de uno, editar y eliminar sus registros pero nos regresará los datos en "crudo", pues obtendremos un Map con las propiedades de los usuarios.
```java
package org.bedu;

import java.util.List;
import java.util.Map;

public interface ConectorHttp {
    List<Map<String, Object>> consultarTodos();
    Map<String, Object> encontrarPorId(int id);
    Map<String, Object> editar(Map<String, Object> usuario);
    boolean eliminar(int id);
}
```

3. Agregaremos ahora un bean que describa el objeto Usuario, mismo que construiremos a partir de las propiedades obtenidas por la API:
```java
package org.bedu;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String correo;
    private LocalDate fechaRegistro;
}
```
Además de los getter y setter para cada propiedad.

4. Finalmente, agregaremos una clase llamada UsuariosService, que será la encargada de realizar las operaciones con los usuarios llamando a la API, además de recibir la información en crudo y generar el objeto Usuario que requerimos en nuestra aplicación:
```java
package org.bedu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsuariosService {
    private ConectorHttp api;

    public UsuariosService(ConectorHttp api) {
        this.api = api;
    }

    public ArrayList<Usuario> consultarTodos(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for(Map<String, Object> registro : api.consultarTodos()){
            usuarios.add(crearUsuarioDeMap(registro));
        }
        return usuarios;
    }

    public Usuario encontrarPorId(int id){
        return crearUsuarioDeMap(api.encontrarPorId(id));
    }

    public Usuario editar(Usuario usuario){
        Map<String, Object> datosUsuario = crearMapDeUsuario(usuario);
        return crearUsuarioDeMap(api.editar(datosUsuario));
    }

    public boolean eliminar(Usuario usuario){
        return api.eliminar(usuario.getId());
    }

    private Usuario crearUsuarioDeMap(Map<String, Object> datosUsuario) {
        Usuario usuario = new Usuario();

        usuario.setId((Integer)datosUsuario.get("id"));
        usuario.setNombreUsuario((String)datosUsuario.get("nombreUsuario"));
        usuario.setCorreo((String)datosUsuario.get("correo"));
        usuario.setFechaRegistro((LocalDate)datosUsuario.get("fechaRegistro"));

        return usuario;
    }

    private Map<String, Object> crearMapDeUsuario(Usuario usuario) {
        HashMap<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("id", usuario.getId());
        datosUsuario.put("nombreUsuario", usuario.getNombreUsuario());
        datosUsuario.put("correo", usuario.getCorreo());
        datosUsuario.put("fechaRegistro", usuario.getFechaRegistro());

        return datosUsuario;
    }
}

```

5. Finalmente, crearemos el esqueleto de la clase UsuariosServiceTest en src/test/java, en la cual realizaremos las pruebas a nuestro servicio y es donde se requerirá el uso de Mockito:
```java
package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuariosServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void consultarTodosTest() {
    }

    @Test
    void encontrarPorIdTest() {
    }

    @Test
    void editarTest() {
    }

    @Test
    void eliminarTest() {
    }
}
```

<details>
	<summary>Solución</summary>
    1. El primer paso consiste en agregar las propiedades necesarias a la clase de prueba para poder crear y utilizar los mocks, para ello agregaremos los siguientes atributos con sus anotaciones:
    ```java
    @Mock
    private ConectorHttp api;
    @InjectMocks
    private UsuariosService servicio;
    ```

    2. Después, dentro del método setUp agregaremos la siguiente línea que le dirá a Mockito dónde insertar nuestro objeto Mock:
    ```java
	MockitoAnnotations.initMocks(this);
    ```

	3. Finalmente, podremos describir el comportamiento esperado en cada uno de los casos de prueba, así como las aserciones correspondientes y los métodos de utilería para usar de apoyo en la creación de objetos:
	```java
    package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UsuariosServiceTest {
    @Mock
    private ConectorHttp api;
    @InjectMocks
    private UsuariosService servicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void consultarTodosTest() {
        Mockito.when(api.consultarTodos()).thenReturn(crearListMapUsuarios());

        ArrayList<Usuario> resultado = servicio.consultarTodos();

        assertEquals(1, resultado.size());
    }

    @Test
    void encontrarPorIdTest() {
        Mockito.when(api.encontrarPorId(1)).thenReturn(crearMapUsuario());

        Usuario resultado = servicio.encontrarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void editarTest() {
        Mockito.when(api.editar(Mockito.anyMap())).thenReturn(crearMapUsuario());

        Usuario resultado = servicio.editar(crearUsuario());

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void eliminarTest() {
        Mockito.when(api.eliminar(1)).thenReturn(true);

        boolean resultado = servicio.eliminar(crearUsuario());

        assertTrue(resultado);
    }

    private List<Map<String, Object>> crearListMapUsuarios() {
        HashMap<String, Object> usuario = crearMapUsuario();
        return Collections.singletonList(usuario);
    }

    private HashMap<String, Object> crearMapUsuario() {
        HashMap<String, Object> usuario = new HashMap<>();
        usuario.put("id", 1);
        usuario.put("nombreUsuario", "admin");
        usuario.put("correo", "admin@mail.com");
        usuario.put("fechaRegistro", LocalDate.now());
        return usuario;
    }

    private Usuario crearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombreUsuario("admin");
        usuario.setCorreo("admin@mail.com");
        usuario.setFechaRegistro(LocalDate.now());
        return usuario;
    }
}
    ```
</details>
