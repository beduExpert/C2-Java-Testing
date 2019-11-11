package org.bedu;

import java.util.List;

public class AdministradorUsuarios {
    UsuarioRepositorio repositorio;

    public AdministradorUsuarios(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario guardarUsuario(String username, String correo, String descripcion, List coloresFavoritos){
        Usuario usuario = new Usuario(username, correo, descripcion, coloresFavoritos);
        Usuario tmp = repositorio.guardar(usuario);
        return tmp;
    }

    public Usuario editarUsuario(int id, String username, String correo, String descripcion, List coloresFavoritos){
        Usuario encontrado = repositorio.buscarPorId(id);
        encontrado.setId(id);
        encontrado.setUsername(username);
        encontrado.setCorreo(correo);
        encontrado.setDescripcion(descripcion);
        encontrado.setColoresFavoritos(coloresFavoritos);

        Usuario tmp = repositorio.reemplazar(id, encontrado);
        return tmp;
    }

    public boolean eliminarUsuario(int id){
        boolean resultado = repositorio.eliminar(id);
        return resultado;
    }
}
