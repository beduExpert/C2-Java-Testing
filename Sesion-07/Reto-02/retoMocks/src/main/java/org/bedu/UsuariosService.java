package org.bedu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsuariosService {
    private ConectorHttp api;

    public UsuariosService(ConectorHttp api) {
        this.api = api;
    }

    public ArrayList<Usuario> consultarTodos(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for(Map<String, Object> registro : api.consultarTodos()){
            usuarios.add(crearUsuarioDeMap(registro));
        }
        return usuarios;
    }

    public Usuario encontrarPorId(int id){
        return crearUsuarioDeMap(api.encontrarPorId(id));
    }

    public Usuario editar(Usuario usuario){
        Map<String, Object> datosUsuario = crearMapDeUsuario(usuario);
        return crearUsuarioDeMap(api.editar(datosUsuario));
    }

    public boolean eliminar(Usuario usuario){
        return api.eliminar(usuario.getId());
    }

    private Usuario crearUsuarioDeMap(Map<String, Object> datosUsuario) {
        Usuario usuario = new Usuario();

        usuario.setId((Integer)datosUsuario.get("id"));
        usuario.setNombreUsuario((String)datosUsuario.get("nombreUsuario"));
        usuario.setCorreo((String)datosUsuario.get("correo"));
        usuario.setFechaRegistro((LocalDate)datosUsuario.get("fechaRegistro"));

        return usuario;
    }

    private Map<String, Object> crearMapDeUsuario(Usuario usuario) {
        HashMap<String, Object> datosUsuario = new HashMap<>();

        datosUsuario.put("id", usuario.getId());
        datosUsuario.put("nombreUsuario", usuario.getNombreUsuario());
        datosUsuario.put("correo", usuario.getCorreo());
        datosUsuario.put("fechaRegistro", usuario.getFechaRegistro());

        return datosUsuario;
    }
}
