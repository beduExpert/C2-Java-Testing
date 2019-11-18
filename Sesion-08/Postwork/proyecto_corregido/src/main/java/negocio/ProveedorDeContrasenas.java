package negocio;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import exception.email.AutomaticEmailException;
import modelo.Usuario;
import persistencia.UsuarioDAO;

public class ProveedorDeContrasenas {
	
	private static final String SALTOS = "\n\n ";
	private String[] correos;
	private String[] nombres;
	private String[] contras;

	public boolean enviaContrasenas(UsuarioDAO usuarioDAO, List<Usuario> listaUsuarios, List<String> contrasenas)
			throws AutomaticEmailException, IOException {
		boolean envioCompleto = false;
		correos = new String[listaUsuarios.size()];
		nombres = new String[listaUsuarios.size()];
		contras = new String[listaUsuarios.size()];

		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		String cadenaFecha = formato.format(fechaActual);

		obtencionDeCorreoYNombre(listaUsuarios);
		obtencionDeContrasenas(contrasenas);

		try {
			ResourceBundle properties = getArchivoDePropiedades();
			envioDeContrasenas(properties, cadenaFecha, listaUsuarios, usuarioDAO);
			envioCompleto = true;
			return envioCompleto;
		} catch (EmailException | NumberFormatException | SQLException e) {
			throw new AutomaticEmailException("Existió un fallo en el envío del correo: ", e);
		}

	}

	private ResourceBundle getArchivoDePropiedades() {
		return ResourceBundle.getBundle("datos-smtp");
	}

	private void envioDeContrasenas(ResourceBundle properties, String cadenaFecha, List<Usuario> listaUsuarios,
			UsuarioDAO usuarioDAO) throws SQLException, EmailException {
		for (int j = 0; j < nombres.length; j++) {
			Email email = new SimpleEmail();
			email.setHostName(properties.getString("host_name"));
			email.setSmtpPort(Integer.parseInt(properties.getString("smtp_port")));
			email.setSubject(properties.getString("subject"));
			email.setFrom(properties.getString("set_from"),
					properties.getString("set_from_title"));
			email.addTo(correos[j], nombres[j]);
			email.setMsg(SALTOS + properties.getString("msg_part1") + " " + nombres[j]
					+ properties.getString("msg_part2") + " " + cadenaFecha + SALTOS
					+ properties.getString("msg_part3") + " " + contras[j] + SALTOS
					+ properties.getString("msg_part4"));
			email.setTLS(true);

			listaUsuarios.get(j).setPassword(contras[j]);
			usuarioDAO.guardaUsuario(listaUsuarios.get(j));
		}
	}

	private void obtencionDeContrasenas(List<String> contrasenas) {
		for (int j = 0; j < contras.length; j++) {
			contras[j] = contrasenas.get(j);
		}
	}

	private void obtencionDeCorreoYNombre(List<Usuario> listaUsuarios) {
		int i = 0;
		for (Usuario usuario : listaUsuarios) {
			correos[i] = usuario.getEmail();
			nombres[i] = usuario.getNombreCompleto();
			i++;
		}
	}
}
