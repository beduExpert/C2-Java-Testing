package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorNombresTest {
    @Mock
    private Lector lector;
    @InjectMocks
    private ExtractorNombres extractor;

    @BeforeEach
    void configurar(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void leerNombreCorrectoTest() {
        //Arrange
        Mockito.when(lector.leerLinea())
                .thenReturn("Pérez López, Juan Fernando")
                .thenReturn(null);
        Nombre esperado = new Nombre();
        esperado.setNombres("Juan Fernando");
        esperado.setApellidoPaterno("Pérez");
        esperado.setApellidoMaterno("López");
        //Actp
        ArrayList<Nombre> resultado = extractor.leerNombres();
        //Assert
        assertTrue(resultado.contains(esperado));
    }

}