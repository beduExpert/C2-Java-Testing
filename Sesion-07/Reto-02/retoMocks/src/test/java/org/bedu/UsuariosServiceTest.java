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