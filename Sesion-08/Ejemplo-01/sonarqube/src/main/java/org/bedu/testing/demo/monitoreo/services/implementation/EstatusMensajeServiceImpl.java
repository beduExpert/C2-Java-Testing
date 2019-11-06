package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.repository.EstablecimientoRepository;
import org.bedu.testing.demo.monitoreo.repository.MensajeRepository;
import org.bedu.testing.demo.monitoreo.services.EstatusMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Service
@Secured("IS_AUTHENTICATED_FULLY")
public class EstatusMensajeServiceImpl implements EstatusMensajeService {

	private final MensajeRepository mensajeRepository;
	private final EstablecimientoRepository establecimientoRepository;

	@Override
	public boolean mensajesNoLeidosDeEstablecimiento(Establecimiento establecimiento) {
		List<Mensaje> messagesList = mensajeRepository.findByEstablecimientoAndLeido(establecimiento, false);
		return !messagesList.isEmpty();
	}

	@Override
	public boolean mensajesNoLeidosDeEstablecimiento(Long idEstablecimiento) {
		return mensajesNoLeidosDeEstablecimiento(establecimientoRepository.findByIdEstablecimiento(idEstablecimiento));
	}

}
