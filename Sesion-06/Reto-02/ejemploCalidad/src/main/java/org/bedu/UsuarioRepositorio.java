package org.bedu;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    List usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new ArrayList();
    }

    public Usuario guardar(Usuario usuario) {
        usuario.setId(usuarios.size());
        if(usuarios.add(usuario) == true) {
            return usuario;
        }
        else {
            return null;
        }
    }

    public Usuario buscarPorId(int id){
        Usuario encontrado = null;
        try{
            encontrado = (Usuario) usuarios.get(id);
        }catch (Exception e){

        }
        return encontrado;
    }

    public Usuario reemplazar(int id, Usuario nuevo){
        Usuario guardado = null;
        nuevo.setId(id);
        try{
            guardado = (Usuario) usuarios.set(id, nuevo);
        } catch(Exception e){

        }
        return guardado;
    }

    public boolean eliminar(int id){
        boolean resultado = true;
        try{
            usuarios.remove(id);
        }catch (Exception e){
            resultado = false;
        }
        return resultado;
    }
}
