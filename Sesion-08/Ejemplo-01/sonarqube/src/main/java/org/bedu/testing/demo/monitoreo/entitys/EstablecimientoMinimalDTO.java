package org.bedu.testing.demo.monitoreo.entitys;

import lombok.Data;

@Data
public class EstablecimientoMinimalDTO {
	private static final String ADDRESS_TEMPLATE = "%s, %s";

	private Long idEstablecimiento;
	private String direccion;

	public EstablecimientoMinimalDTO(Long idEstablecimiento, String calleYNumero, String colonia) {
		this.idEstablecimiento = idEstablecimiento;
		this.direccion = String.format(ADDRESS_TEMPLATE, calleYNumero, colonia);
	}

}
