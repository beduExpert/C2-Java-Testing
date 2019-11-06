package org.bedu.retoBaseDatos.service;

import org.bedu.retoBaseDatos.Pago;
import org.bedu.retoBaseDatos.repository.PagosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PagosServiceTest {
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
}