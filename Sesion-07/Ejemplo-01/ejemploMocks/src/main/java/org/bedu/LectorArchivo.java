package org.bedu;

import java.io.*;

public class LectorArchivo implements Lector {

    private BufferedReader reader;

    public LectorArchivo(String nombreArchivo) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(nombreArchivo));
    }

    @Override
    public String leerLinea() {
        String leido;
        try {
            leido = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            leido = null;
        }
        return leido;
    }
}
