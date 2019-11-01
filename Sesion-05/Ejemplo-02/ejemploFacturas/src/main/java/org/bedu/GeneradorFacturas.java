package org.bedu;

public class GeneradorFacturas {
    private static final String MSJ_FACTURA = "Estimado %s, por favor pague su factura correspondiente al mes actual por un monto de $%d";

    private Enviador correo;

    public GeneradorFacturas(){
        correo = new EnviadorCorreos();
    }

    public GeneradorFacturas(Enviador correo){
        this.correo = correo;
    }

    public boolean crearFactura(String destinatario, int cantidad){
        String cuerpoCorreo = String.format(MSJ_FACTURA, destinatario, cantidad);

        return correo.enviarCorreo(destinatario, cuerpoCorreo);
    }
}
