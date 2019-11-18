package persistencia;

import java.sql.SQLException;
import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {
	List<Usuario> getListaUsuarios();

	void guardaUsuario(Usuario usuario) throws SQLException;

	void eliminaUsuario(Usuario usuario) throws SQLException;

	Usuario buscaUsuario(String username) throws SQLException;
}
