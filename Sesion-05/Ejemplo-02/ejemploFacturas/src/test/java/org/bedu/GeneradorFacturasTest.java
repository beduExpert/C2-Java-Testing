package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneradorFacturasTest {

    private GeneradorFacturas generador = new GeneradorFacturas();

    @BeforeEach
    void setUp() {
        generador = new GeneradorFacturas(new EnviadorCorreosFalso());
    }

    @Test
    void crearFacturaTest() {
        //Act
        boolean resultado = generador.crearFactura("user@mail.com", 1500);
        //Assert
        assertTrue(resultado);
    }
}