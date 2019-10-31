package org.bedu.testing.fizzbuzz;

public class FizzBuzz {
	public static String valorSecuencia(int valor) {

		String regreso = "";
		
		if(valor == 0) {
			return "0";
		}
		
		if(valor % 3 == 0 || String.valueOf(valor).contains("3")) {
			regreso +=  "Fizz";
		} 
		
		if(valor % 5 == 0) {
			regreso += "Buzz";
		}
		
		
		return regreso.isEmpty() ? String.valueOf(valor) : regreso;
	}
}
