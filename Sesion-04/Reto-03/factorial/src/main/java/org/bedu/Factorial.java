package org.bedu;

public class Factorial {
	public long calcular(long x) {
		long acumulado = 1;

		if (x > 20 || x < 0) {
			throw new IllegalArgumentException("El valor de x no es válido");
		}

		for (; x > 1; x--) {
			acumulado *= x;
		}
		return acumulado;
	}
}
