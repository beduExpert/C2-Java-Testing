package org.bedu;

public class Factorial {
    public long calcular(long x){
        long acumulado = 1;
        for(; x > 1; x--){
            acumulado*= x;
        }
        return acumulado;
    }
}
