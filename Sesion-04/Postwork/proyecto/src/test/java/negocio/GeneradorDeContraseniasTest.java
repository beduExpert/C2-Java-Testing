package negocio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeneradorDeContraseniasTest {
	private static GeneradorDeContrasenias generador;

	@BeforeEach
	public void inicializa() throws NoSuchAlgorithmException {
		generador = new GeneradorDeContrasenias(10);
		generador.generaTodasLasContrasenias(12);
	}

	@Test
	public void pruebaGeneradorNoDevuelveListaNulaDeElementos() {
		assertNotNull(generador.getContrasenas());
	}
	
	@Test
	void contraseniasParametrizadas() {
		System.out.println(generador.getContrasenas());
	}
}
