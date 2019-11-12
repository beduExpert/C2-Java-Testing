package org.bedu.testing.demo.monitoreo.entitys;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstablecimientoDTO {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

	private Long idEstablecimiento;

	private String nombreEstablecimiento;

	private String calleYNumero;

	private String referencia;

	private String telefono;

	private String correo;

	private String contacto;

	private TipoEstablecimiento tipoEstablecimiento;

	private Zona zona;

	private Colonia colonia;

	private String estatus;

	private Boolean mensajes;

	private String ultimaVisita;

	private BigDecimal latitud;

	private BigDecimal longitud;

	public static EstablecimientoDTO of(Establecimiento establecimiento) {

		LocalDate ultimaVisita = getLastVisit(establecimiento);

		//@formatter:off
			return EstablecimientoDTO.builder()
			.calleYNumero(establecimiento.getCalleYNumero())
			.colonia(establecimiento.getColonia())
			.contacto(establecimiento.getContacto())
			.estatus( establecimiento.getEstatus().toString())
			.idEstablecimiento(establecimiento.getIdEstablecimiento())
			.nombreEstablecimiento(establecimiento.getNombreEstablecimiento())
			.referencia(establecimiento.getReferencia())
			.telefono(establecimiento.getTelefono())
			.correo(establecimiento.getCorreo())
			.tipoEstablecimiento(establecimiento.getTipoEstablecimiento())
			.ultimaVisita(ultimaVisita == null ? "No visitado" : FORMATTER.format(ultimaVisita))
			.latitud(establecimiento.getLatitud())
			.longitud(establecimiento.getLongitud())
			.zona(establecimiento.getZona())
			.build();
			//@formatter:on

	}

	private static LocalDate getLastVisit(Establecimiento establecimiento) {
		LocalDate ultimaVisita = null;

		List<Visita> listaOrdenada = establecimiento.getVisitas();
		if (listaOrdenada != null) {
			Collections.sort(listaOrdenada);
			Collections.reverse(listaOrdenada);
			ultimaVisita = listaOrdenada.isEmpty() ? null : listaOrdenada.get(0).getFecha().toLocalDate();
		}
		return ultimaVisita;
	}

	public static EstablecimientoDTO of(EstablecimientoVisitaView establecimiento) {

		Visita ultimaVisitaObj = establecimiento.getUltimaVisita();

		//@formatter:off
			return EstablecimientoDTO.builder()
			.calleYNumero(establecimiento.getCalleYNumero())
			.colonia(establecimiento.getColonia())
			.contacto(establecimiento.getContacto())
			.estatus( WordUtils.capitalizeFully(establecimiento.getEstatus().toString()))
			.idEstablecimiento(establecimiento.getIdEstablecimiento())
			.nombreEstablecimiento(establecimiento.getNombreEstablecimiento())
			.referencia(establecimiento.getReferencia())
			.telefono(establecimiento.getTelefono())
			.correo(establecimiento.getCorreo())
			.tipoEstablecimiento(establecimiento.getTipoEstablecimiento())
			.ultimaVisita(ultimaVisitaObj == null ? "No visitado" : FORMATTER.format(ultimaVisitaObj.getFecha()))
			.latitud(establecimiento.getLatitud())
			.longitud(establecimiento.getLongitud())
			.zona(establecimiento.getZona())
			.build();
			//@formatter:on

	}

}
