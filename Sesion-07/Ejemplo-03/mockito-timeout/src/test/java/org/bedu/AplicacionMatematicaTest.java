package org.bedu;

import org.bedu.testing.AplicacionMatematica;
import org.bedu.testing.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AplicacionMatematicaTest {

	@Mock
	private Calculadora calculadora;

	@InjectMocks
	private AplicacionMatematica app;

	@Test
	void sumaYRestaMenos100Milisegundos() {

		when(calculadora.suma(20.0, 10.0)).thenReturn(30.0);

		when(calculadora.resta(20.0, 10.0)).thenReturn(10.0);


		Assertions.assertEquals(app.resta(20.0, 10.0), 10.0, 0);

		Assertions.assertEquals(app.suma(20.0, 10.0), 30.0, 0);

		verify(calculadora, Mockito.timeout(100)).suma(20.0, 10.0);

		verify(calculadora, Mockito.timeout(100).times(1)).resta(20.0, 10.0);
	}
}
