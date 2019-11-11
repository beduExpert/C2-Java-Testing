package org.bedu;

import java.util.Arrays;

public class App {
	private static final String AGREGAR = "agregar";
	private static final String ELIMINAR = "eliminar";

	public static void main(String[] args) {
		String operacionARealizar = args[0];

		AdministradorUsuarios admin = new AdministradorUsuarios(new UsuarioRepositorio());
		admin.guardarUsuario("juanlopez", "jlopez@mail.com", "Usuario dummy para llenar la bd",
				Arrays.asList(new String[] { "amarillo" }));

		if (operacionARealizar.equals(AGREGAR)) {
			System.out.println("Agregando usuario a la bd");
			admin.guardarUsuario("mariaperez", "maria@mail.com", "Usuario dummy para llenar la bd",
					Arrays.asList(new String[] { "rosa" }));
		} else if (operacionARealizar.equals(ELIMINAR)) {
			admin.eliminarUsuario(2);
		}
	}
}
