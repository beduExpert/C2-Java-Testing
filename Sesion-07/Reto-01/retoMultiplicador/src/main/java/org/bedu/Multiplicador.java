package org.bedu;

public class Multiplicador {
    private Sumador sumador;

    public int multiplicar(int x, int y){
        int resultado = 0;
        for (; y > 0; y--) {
            resultado = sumador.sumar(resultado, x);
        }
        return resultado;
    }
}
