package org.bedu;

public class Calculadora {
	private Double sumar(Double a, Double b) {
		return a + b;
	}

	private Double restar(Double a, Double b) {
		return a - b;
	}

	private Double multiplicar(Double a, Double b) {
		return a * b;
	}

	private Double dividir(Double a, Double b) {
		return a / b;
	}

	public Double calcular(String operacion, Double a, Double b) throws IllegalArgumentException {
		
		if(a == null || b == null){
            throw new IllegalArgumentException("Se esperaba el valor para la operación");
        }
		
		switch (operacion) {
		case "sumar":
			return sumar(a, b);
		case "restar":
			return restar(a, b);
		case "multiplicar":
			return multiplicar(a, b);
		case "dividir":
			return dividir(a, b);
		case "":
			throw new IllegalArgumentException("Se esperaba el nombre de la operación");
		}
		return 0.0;
	}
}
