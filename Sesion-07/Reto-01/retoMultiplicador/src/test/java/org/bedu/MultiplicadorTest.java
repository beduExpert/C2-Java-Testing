package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicadorTest {
    @Mock
    private Sumador sumador;
    @InjectMocks
    private Multiplicador mult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void multiplicar2y2Test() {
        Mockito.when(sumador.sumar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(4);
        int esperado = 4;

        int resultado = mult.multiplicar(2, 2);

        assertEquals(esperado, resultado);
    }

    @Test
    void multiplicar4y5Test() {
        Mockito.when(sumador.sumar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(20);
        int esperado = 20;

        int resultado = mult.multiplicar(5, 4);

        assertEquals(esperado, resultado);
    }
}