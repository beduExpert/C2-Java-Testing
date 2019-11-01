package org.bedu;

import java.io.IOException;

public interface Lector {
    String leerArchivo(String nombreArchivo) throws IOException;
}
