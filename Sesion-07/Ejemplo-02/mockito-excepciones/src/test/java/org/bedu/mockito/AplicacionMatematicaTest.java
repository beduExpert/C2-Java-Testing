package org.bedu.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AplicacionMatematicaTest {

	@Mock
	private Calculadora calculadora;
	
	@InjectMocks
	private AplicacionMatematica app;

	@BeforeEach
    void configurar(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	 void sumaNegativosLanzaExcepcion(){
	      Mockito.doThrow(new RuntimeException("Add operation not implemented"))
	         .when(calculadora).suma(-10.0, -20.0);

	      Assertions.assertThrows(RuntimeException.class, () -> {
	    		calculadora.suma(-10.0, -20.0);
	    	}); 
	   }
}
