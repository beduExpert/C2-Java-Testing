package org.bedu.testing.demo.monitoreo.entitys;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VisitaDetailDTO {

	private String nombre;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private boolean visitada;

}