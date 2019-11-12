package org.bedu.testing.demo.monitoreo.entitys.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ColoniaCompositeKey implements Serializable {

	private static final long serialVersionUID = 20190307L;

	@Column(name = "codigo_postal")
	private String codigoPostal;

	@Column(name = "id_colonia")
	private Long idColonia;

}
