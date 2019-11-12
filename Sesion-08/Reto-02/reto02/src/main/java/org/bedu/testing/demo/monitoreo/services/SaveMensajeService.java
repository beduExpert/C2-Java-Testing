package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Mensaje;

public interface SaveMensajeService {

	Mensaje nuevoMensaje(String mensaje, Long idEstablecimiento);
}
