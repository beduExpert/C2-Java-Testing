package org.bedu;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.omg.CORBA.DynAnyPackage.Invalid;

public class Calculadora {
    private Double sumar(Double a, Double b){
        return a + b;
    }

    private Double restar(Double a, Double b){
        return a - b;
    }

    private Double multiplicar(Double a, Double b){
        return a * b;
    }

    private Double dividir(Double a, Double b){
        return a / b;
    }

    public Double calcular(String operacion, Double a, Double b) throws InvalidArgumentException {
        if(a == null || b == null){
            throw new InvalidArgumentException(new String[]{"Se esperaba el valor para la operación"});
        }
        if(operacion == null){
            throw new InvalidArgumentException(new String[]{"Se esperaba el nombre de la operación"});
        }
        switch (operacion){
            case "sumar":
                return sumar(a, b);
            case "restar":
                return restar(a, b);
            case "multiplicar":
                return multiplicar(a, b);
            case "dividir":
                return dividir(a, b);
            case "":
                throw new InvalidArgumentException(new String[]{"Se esperaba el nombre de la operación"});
        }
        return 0.0;
    }
}
