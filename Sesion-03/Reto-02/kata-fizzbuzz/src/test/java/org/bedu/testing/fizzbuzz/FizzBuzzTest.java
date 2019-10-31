package org.bedu.testing.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

	@Test
	void ceroEsCero() {
		assertEquals("0", FizzBuzz.valorSecuencia(0));
	}
	
	@Test
	void unoEsUno() {
		assertEquals("1", FizzBuzz.valorSecuencia(1));
	}
	
	@Test
	void tresEsFizz() {
		assertEquals("Fizz", FizzBuzz.valorSecuencia(3));
	}
	
	@Test
	void cincoEsBuzz() {
		assertEquals("Buzz", FizzBuzz.valorSecuencia(5));
	}
	
	@Test 
	void seisEsFizz() {
		assertEquals("Fizz", FizzBuzz.valorSecuencia(6));
	}

	@Test 
	void diezEsBuzz() {
		assertEquals("Buzz", FizzBuzz.valorSecuencia(10));
	}
	
	@Test 
	void quinceEsFizzBuzz() {
		assertEquals("FizzBuzz", FizzBuzz.valorSecuencia(15));
	}
	
	@Test
	void treceEsFizz() {
		assertEquals("Fizz", FizzBuzz.valorSecuencia(13));
	}
	
	@Test
	void veinticincoEsBuzz() {
		assertEquals("Buzz", FizzBuzz.valorSecuencia(25));
	}
	
	@Test
	void treintaycincoEsFizzBuzz() {
		assertEquals("FizzBuzz", FizzBuzz.valorSecuencia(135));
	}
}
