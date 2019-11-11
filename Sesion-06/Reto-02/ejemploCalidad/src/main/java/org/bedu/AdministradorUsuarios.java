package org.bedu;

import java.util.List;

public class AdministradorUsuarios {
    UsuarioRepositorio repositorio;

    public AdministradorUsuarios(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario guardarUsuario(String username, String correo, String descripcion, List coloresFavoritos){
        Usuario usuario = new Usuario(username, correo, descripcion, coloresFavoritos);
        return repositorio.guardar(usuario);
    }

    public Usuario editarUsuario(Usuario nuevo){
    	return repositorio.reemplazar(nuevo.getId(), nuevo);
    }

    public boolean eliminarUsuario(int id){
        boolean resultado = repositorio.eliminar(id);
        return resultado;
    }
}
