package org.bedu;

import java.util.logging.Logger;

public class EnviadorCorreos implements Enviador {

    private Logger log = Logger.getLogger("EnviadorCorreos");

    @Override
    public boolean enviarCorreo(String destinatario, String mensaje){
        log.info(String.format("Enviando correo a %s", destinatario));
        log.info(String.format("Cuerpo del correo:%n %s",mensaje));

        log.info("Corre enviado");

        return true;
    }
}
