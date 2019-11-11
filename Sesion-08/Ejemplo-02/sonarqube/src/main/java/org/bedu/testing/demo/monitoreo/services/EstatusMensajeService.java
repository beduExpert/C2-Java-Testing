package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;

public interface EstatusMensajeService {

	boolean mensajesNoLeidosDeEstablecimiento(Establecimiento establecimiento);

	boolean mensajesNoLeidosDeEstablecimiento(Long idEstablecimiento);
}
