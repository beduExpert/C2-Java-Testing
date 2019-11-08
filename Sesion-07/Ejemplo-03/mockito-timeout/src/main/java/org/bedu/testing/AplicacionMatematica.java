package org.bedu.testing;

public class AplicacionMatematica {
	private Calculadora calculadora;

	public void setCalculadora(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public double suma(double a, double b) {
		return calculadora.suma(a, b);
	}

	public double resta(double a, double b) {
		return calculadora.resta(a, b);
	}

	public double multiplica(double a, double b) {
		return calculadora.multiplica(a, b);
	}

	public double divide(double a, double b) {
		return calculadora.divide(a, b);
	}
}
