package org.bedu.testing.demo.monitoreo.tools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.Visita;
import org.bedu.testing.demo.monitoreo.entitys.VisitaDetailDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VisitaDetailFactory {

	public static Iterable<VisitaDetailDTO> fromRutaAsignada(RutaAsignada ruta) {
		List<VisitaDetailDTO> resultado = new ArrayList<>();

		if (ruta == null || ruta.getEstablecimiento() == null) {
			return resultado;
		}

		for (Establecimiento e : ruta.getEstablecimiento()) {
			VisitaDetailDTO vdto = new VisitaDetailDTO();
			vdto.setNombre(e.getNombreEstablecimiento());
			vdto.setLatitud(e.getLatitud());
			vdto.setLongitud(e.getLongitud());
			vdto.setVisitada(checkVisited(e));

			resultado.add(vdto);
		}

		return resultado;

	}

	private boolean checkVisited(Establecimiento e) {
		List<Visita> visitas = e.getVisitas();

		if (visitas.isEmpty()) {
			return false;
		}

		Collections.sort(visitas);
		Collections.reverse(visitas);

		return visitas.get(0).getFecha().toLocalDate().isEqual(LocalDate.now());
	}
}
