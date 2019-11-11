package org.bedu.testing.demo.monitoreo.entitys.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioCompositeKey implements Serializable {

	private static final long serialVersionUID = 20190305L;

	@Column(name = "id_municipio")
	private Long idMunicipio;

	@Column(name = "fk_estado")
	private Long idEstado;

}
