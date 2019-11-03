package org.bedu.testing.stringcalculator;

import java.util.Arrays;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {

	public int add(String numeros) {
		if(numeros.isEmpty())
			return 0;

		Stream<String> elementos = Arrays.stream(numeros.split(","));
		return elementos.mapToInt(Integer::parseInt).sum();
	}
}
