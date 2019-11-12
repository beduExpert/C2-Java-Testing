package org.bedu.testing.demo.monitoreo.tools;

import java.util.ArrayList;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoVisitaView;
import org.bedu.testing.demo.monitoreo.services.EstatusMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class EstablecimientoEstablecimientoDTOConverter {

	@Setter(onMethod_ = { @Autowired })
	private EstatusMensajeService mensajeService;

	public Page<EstablecimientoDTO> convert(Page<Establecimiento> pageEstablecimientos) {
		List<Establecimiento> establecimientos = pageEstablecimientos.getContent();

		List<EstablecimientoDTO> establecimientosDTO = convert(establecimientos);

		return new PageImpl<>(establecimientosDTO, pageEstablecimientos.getPageable(),
				pageEstablecimientos.getTotalElements());
	}

	public Page<EstablecimientoDTO> convertView(Page<EstablecimientoVisitaView> pageEstablecimientos) {
		List<EstablecimientoVisitaView> establecimientos = pageEstablecimientos.getContent();

		List<EstablecimientoDTO> establecimientosDTO = convertView(establecimientos);

		return new PageImpl<>(establecimientosDTO, pageEstablecimientos.getPageable(),
				pageEstablecimientos.getTotalElements());
	}

	public List<EstablecimientoDTO> convert(List<Establecimiento> listEstablecimientos) {
		List<EstablecimientoDTO> establecimientosDTO = new ArrayList<>();
		for (Establecimiento e : listEstablecimientos) {

			EstablecimientoDTO edto = EstablecimientoDTO.of(e);
			edto.setMensajes(mensajeService.mensajesNoLeidosDeEstablecimiento(e));

			establecimientosDTO.add(edto);
		}

		return establecimientosDTO;

	}

	public List<EstablecimientoDTO> convertView(List<EstablecimientoVisitaView> listEstablecimientos) {
		List<EstablecimientoDTO> establecimientosDTO = new ArrayList<>();
		for (EstablecimientoVisitaView e : listEstablecimientos) {

			EstablecimientoDTO edto = EstablecimientoDTO.of(e);
			edto.setMensajes(mensajeService.mensajesNoLeidosDeEstablecimiento(e.getIdEstablecimiento()));

			establecimientosDTO.add(edto);
		}

		return establecimientosDTO;

	}

}
