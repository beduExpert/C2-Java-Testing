package org.bedu.testing.stringcalculator;

import java.util.Arrays;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {

	public int add(String numeros) {
		if (numeros.isEmpty())
			return 0;

		String negativos = getNumeros(numeros).filter(n -> n < 0).mapToObj(Integer::toString)
				.collect(Collectors.joining(","));
		
		if (!negativos.isEmpty()) {
			throw new IllegalArgumentException("no se admiten números negativos [" + negativos +"]");
		}

		return getNumeros(numeros).sum();
	}

	private IntStream getNumeros(String numeros) {
		return Arrays.stream(numeros.split(",")).mapToInt(Integer::parseInt);
	}
}
