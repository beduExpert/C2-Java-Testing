package org.bedu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeparadorPalabrasTest {

    private SeparadorPalabras separador;
    @BeforeEach
    void setUp() {
        separador = new SeparadorPalabras(new LectorArchivosFalso());
    }

    @Test
    void leerPalabrasTest() throws IOException {
        //Arrange
        int numPalabras = 4;
        //Act
        List<String> palabras = separador.leerPalabras("archivo.txt");
        //Assert
        assertEquals(numPalabras, palabras.size());
    }
}