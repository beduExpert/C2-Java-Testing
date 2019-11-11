package org.bedu;

import java.util.List;

public class Usuario {
    private int id;
    private String username;
    private String correo;
    private String descripcion;
    private double campoNoUsado;
    private List coloresFavoritos;

    public Usuario(String username, String correo, String descripcion, List coloresFavoritos) {
        this.username = username;
        this.correo = correo;
        this.descripcion = descripcion;
        this.coloresFavoritos = coloresFavoritos;
    }

    public void setColoresFavoritos(List coloresFavoritos) {
        this.coloresFavoritos = coloresFavoritos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCampoNoUsado() {
        return campoNoUsado;
    }

    public void setCampoNoUsado(double campoNoUsado) {
        this.campoNoUsado = campoNoUsado;
    }

    public List getColoresFavoritos() {
        return coloresFavoritos;
    }
}
