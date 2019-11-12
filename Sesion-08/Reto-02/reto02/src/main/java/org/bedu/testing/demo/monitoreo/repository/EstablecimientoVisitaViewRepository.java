package org.bedu.testing.demo.monitoreo.repository;

import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoVisitaView;
import org.bedu.testing.demo.monitoreo.enums.ResultadoVisita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EstablecimientoVisitaViewRepository
		extends PagingAndSortingRepository<EstablecimientoVisitaView, Long> {

	Page<EstablecimientoVisitaView> findAllByNombreEstablecimientoLikeAndUltimaVisitaResultado(String nombre,
			ResultadoVisita resultadoVisita, Pageable pageRequest);
}
