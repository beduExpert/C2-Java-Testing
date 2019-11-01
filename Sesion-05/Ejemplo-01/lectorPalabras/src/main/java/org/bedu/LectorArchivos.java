package org.bedu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorArchivos implements Lector {
    @Override
    public String leerArchivo(String nombreArchivo) throws IOException {
        byte[] archivo = Files.readAllBytes(Paths.get(nombreArchivo));
        return new String(archivo);
    }
}
