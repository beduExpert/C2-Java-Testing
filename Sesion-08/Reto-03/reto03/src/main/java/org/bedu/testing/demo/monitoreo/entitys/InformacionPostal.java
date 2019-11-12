package org.bedu.testing.demo.monitoreo.entitys;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionPostal {

	private String codigoPostal;
	private String estado;
	private String municipio;
	private List<Colonia> colonias;

}
