/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bedu.testing.demo.monitoreo.entitys.keys.MunicipioCompositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "municipio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio implements Serializable {
	private static final long serialVersionUID = 20190305L;

	@EmbeddedId
	private MunicipioCompositeKey compositeKey;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "fk_estado", updatable = false, insertable = false)
	private Estado estado;
}
