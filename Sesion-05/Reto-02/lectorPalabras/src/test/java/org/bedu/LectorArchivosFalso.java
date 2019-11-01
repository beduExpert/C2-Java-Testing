package org.bedu;

import java.io.IOException;

public class LectorArchivosFalso implements Lector {
    @Override
    public String leerArchivo(String nombreArchivo) throws IOException {
        return "palabras,separadas,por,coma";
    }
}
