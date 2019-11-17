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
	private String[] correos;
	private String[] nombres;
	private String[] contras;

	public boolean enviaContrasenas(UsuarioDAO usuarioDAO, List<Usuario> listaUsuarios, List<String> contrasenas)
			throws AutomaticEmailException, IOException, Exception {
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
		} catch (EmailException e) {
			throw new AutomaticEmailException("Existió un fallo en el envío del correo: ", e);
		} catch (Exception e) {
			throw e;
		}

	}

	private ResourceBundle getArchivoDePropiedades() throws Exception {
		// <editor-fold defaultstate="collapsed" desc="leer archivo de propiedades">
//        Properties properties = new Properties();
//        InputStream is = null;
//        try
//        {
//            is = new FileInputStream("./src/main/resources/datos-smtp.properties");
//            properties.load(is);
//        }
//        catch (IOException e)
//        {
//            throw new IOException(e);
//        }
//        return properties;
		// </editor-fold>
		try {
			return ResourceBundle.getBundle("datos-smtp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void envioDeContrasenas(ResourceBundle properties, String cadenaFecha, List<Usuario> listaUsuarios,
			UsuarioDAO usuarioDAO) throws NumberFormatException, SQLException, EmailException {
		for (int j = 0; j < nombres.length; j++) {
			Email email = new SimpleEmail();
			email.setHostName(properties.getString("host_name").toString());
			email.setSmtpPort(Integer.parseInt(properties.getString("smtp_port").toString()));
			email.setSubject(properties.getString("subject").toString());
			email.setFrom(properties.getString("set_from").toString(),
					properties.getString("set_from_title").toString());
			email.addTo(correos[j], nombres[j]);
			email.setMsg("\n\n " + properties.getString("msg_part1").toString() + " " + nombres[j]
					+ properties.getString("msg_part2").toString() + " " + cadenaFecha + "\n\n "
					+ properties.getString("msg_part3").toString() + " " + contras[j] + "\n\n "
					+ properties.getString("msg_part4").toString());
			email.setTLS(true);

			// TODO: Pendiente la cuenta en smtp de 7i
			// email.send();
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
