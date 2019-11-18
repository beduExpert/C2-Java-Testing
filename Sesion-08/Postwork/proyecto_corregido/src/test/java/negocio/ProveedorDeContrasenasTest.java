package negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import exception.email.AutomaticEmailException;
import modelo.Usuario;
import persistencia.UsuarioDAO;

public class ProveedorDeContrasenasTest {
	
	@Mock
	private UsuarioDAO usuarioDAO;
	
	@InjectMocks
	private ProveedorDeContrasenas proContras;
	
	@BeforeEach
	void configurar() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void envioExitoso() {
		List<Usuario> listaUsuarios = usuarioDAO.getListaUsuarios();

		GeneradorDeContrasenias generador = new GeneradorDeContrasenias(12);
		try {
			generador.generaTodasLasContrasenias(4);
			assertEquals(true, proContras.enviaContrasenas(usuarioDAO, listaUsuarios, generador.getContrasenas()));
		} catch (NoSuchAlgorithmException | AutomaticEmailException | IOException e) {
			fail(e);
		}
	}
}
