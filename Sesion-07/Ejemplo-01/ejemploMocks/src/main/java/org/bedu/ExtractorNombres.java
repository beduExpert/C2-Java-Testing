package org.bedu;

import java.util.ArrayList;

public class ExtractorNombres {
    private Lector lector;

    public ExtractorNombres(Lector lector) {
        this.lector = lector;
    }

    //Lee una línea de la forma "Pérez López, Juan Fernando" y crea el objeto nombre
    //correspondiente identificando cada campo correctamente.
    public ArrayList<Nombre> leerNombres(){
        ArrayList<Nombre> nombres = new ArrayList<>();

        String linea;
        while((linea = lector.leerLinea()) != null){
            String[] nombreCompleto = linea.split(",");
            String[] apellidos = nombreCompleto[0].split(" ");

            Nombre nombre = new Nombre();
            nombre.setApellidoPaterno(apellidos[0].trim());
            nombre.setApellidoMaterno(apellidos[1].trim());
            nombre.setNombres(nombreCompleto[1].trim());

            nombres.add(nombre);
        }
        return nombres;
    }
}
