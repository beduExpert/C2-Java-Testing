package org.bedu.testing.demo.monitoreo.services;

import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoVisitaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstablecimientoVisitaSevice {

	Page<EstablecimientoVisitaView> establecimientosNoEncontradoPorNombre(String nombre, Pageable pageRequest);
}
