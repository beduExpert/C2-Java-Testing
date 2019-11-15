package negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exception.email.AutomaticEmailException;
import modelo.Usuario;
import persistencia.UsuarioDAO;

public class ProveedorDeContrasenasTest {
	private static Usuario usuario1 = new Usuario("piyojr@hotmail.com", "xxxxx1", "Marco Alvarado");
	private static Usuario usuario2 = new Usuario("piyojr@gmail.com", "xxxxx2", "Antonio Arteaga");
	private static Usuario usuario3 = new Usuario("slipkat666@hotmail.com", "xxxxx3", "Pancho Lopez");
	private static Usuario usuario4 = new Usuario("piyuelo@yahoo.es", "xxxxx4", "Piyuelo Alvarado");

	private static UsuarioDAO usuarioDAO;

	@BeforeAll
	public static void llenarBaseDeDatos() {
		try {
			usuarioDAO.guardaUsuario(usuario1);
			usuarioDAO.guardaUsuario(usuario2);
			usuarioDAO.guardaUsuario(usuario3);
			usuarioDAO.guardaUsuario(usuario4);
		} catch (Exception e) {
			throw new InstantiationError();
		}
	}

	@AfterAll
	public static void vaciarBaseDeDatos() {
		try {
			usuarioDAO.eliminaUsuario(usuario1);
			usuarioDAO.eliminaUsuario(usuario2);
			usuarioDAO.eliminaUsuario(usuario3);
			usuarioDAO.eliminaUsuario(usuario4);
		} catch (Exception e) {
			throw new InstantiationError();
		}
		Runtime r = Runtime.getRuntime();
		r.gc();
	}

	@Test
	public void envioExitoso() throws AutomaticEmailException, IOException, NoSuchAlgorithmException, Exception {
		ProveedorDeContrasenas proContras = new ProveedorDeContrasenas();
		List<Usuario> listaUsuarios = usuarioDAO.getListaUsuarios();

		GeneradorDeContrasenias generador = new GeneradorDeContrasenias(12);
		generador.generaTodasLasContrasenias(4);

		assertEquals(true, proContras.enviaContrasenas(usuarioDAO, listaUsuarios, generador.getContrasenas()));
	}
}
