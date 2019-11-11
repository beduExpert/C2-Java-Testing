package org.bedu;

import java.util.logging.Logger;

public class EnviadorCorreosFalso implements Enviador {
    private Logger log = Logger.getLogger("EnviadorCorreosFalso");

    @Override
    public boolean enviarCorreo(String destinatario, String mensaje) {
        log.info(String.format("Fingiendo enviar un correo a %s", destinatario));
        return true;
    }
}
