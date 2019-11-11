package org.bedu.testing.demo.monitoreo.services.implementation;

import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoVisitaView;
import org.bedu.testing.demo.monitoreo.enums.ResultadoVisita;
import org.bedu.testing.demo.monitoreo.repository.EstablecimientoVisitaViewRepository;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoVisitaSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class EstablecimientoVisitaServiceImpl implements EstablecimientoVisitaSevice {
	private static final String MATCH_TEMPLATE = "%%%s%%";

	private final EstablecimientoVisitaViewRepository repository;

	@Override
	public Page<EstablecimientoVisitaView> establecimientosNoEncontradoPorNombre(String nombre, Pageable pageRequest) {

		return repository.findAllByNombreEstablecimientoLikeAndUltimaVisitaResultado(
				String.format(MATCH_TEMPLATE, nombre), ResultadoVisita.NO_ENCONTRADO, pageRequest);
	}

}
