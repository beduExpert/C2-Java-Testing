package org.bedu;

public class Nombre {
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Nombre){
            Nombre otro = (Nombre)obj;
            return otro.nombres.equals(nombres)
            && otro.apellidoPaterno.equals(apellidoPaterno)
            && otro.apellidoMaterno.equals(apellidoMaterno);
        }
        return false;
    }
}
