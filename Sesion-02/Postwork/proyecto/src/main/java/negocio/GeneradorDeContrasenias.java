package negocio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneradorDeContrasenias {

	private List<String> contrasenias = new ArrayList<String>();
	private Set<String> contras = new HashSet<String>();
	private int totalContrasenias;

	public GeneradorDeContrasenias() {
		totalContrasenias = 1;
	}

	public GeneradorDeContrasenias(int totalContrasenias) {
		this.totalContrasenias = totalContrasenias;
	}

	public int getTotalContrasenias() {
		return totalContrasenias;
	}

	public List<String> getContrasenas() {
		return contrasenias;
	}

	// genera las contraseñas con un tamaño definido(64) resultado de aplicar
	// SHA-256
	public void generaTodasLasContrasenias() throws NoSuchAlgorithmException {
		int i = 0;
		Date fecha = new Date();
		while (contras.size() < totalContrasenias) {
			contras.add(generaPassword(String.valueOf(fecha.getTime()) + i++));
		}
		for (String contraActual : contras) {
			contrasenias.add(contraActual);
		}
	}

	// Genera las contraseñas con un tamaño especificado
	public void generaTodasLasContrasenias(int tamanio) throws NoSuchAlgorithmException {
		generaTodasLasContrasenias();
		if (tamanio > 64 || tamanio < 1) {
			throw new IllegalArgumentException(
					"El tamaño de contraseña requerido está fuera del rango permitido (1-64)");
		}
		for (int i = 0; i < contrasenias.size(); i++) {
			contrasenias.set(i, contrasenias.get(i).substring(0, tamanio));
		}

	}

	// aplicación del SHA para generar la contraseña a partir de una cadena
	private String generaPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte byteData[] = md.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}