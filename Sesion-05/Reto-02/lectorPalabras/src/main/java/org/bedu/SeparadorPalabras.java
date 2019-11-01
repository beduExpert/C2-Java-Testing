package org.bedu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SeparadorPalabras {
    private Lector lector;

    public SeparadorPalabras(){
        lector = new LectorArchivos();
    }

    public SeparadorPalabras(Lector lector) {
        this.lector = lector;
    }

    public List<String> leerPalabras(String nombreArchivo) throws IOException {
        String contenidoArchivo = lector.leerArchivo(nombreArchivo);
        String[] palabras = contenidoArchivo.split(",");
        return Arrays.asList(palabras);
    }
}
