package org.bedu.testing.demo.monitoreo.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosEstablecimientoDTO {
	private String telefono;
	private String correo;
	private TipoEstablecimiento tipoEstablecimiento;

}
