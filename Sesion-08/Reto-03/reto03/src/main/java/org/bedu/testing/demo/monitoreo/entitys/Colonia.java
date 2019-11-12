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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bedu.testing.demo.monitoreo.entitys.keys.ColoniaCompositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "colonia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colonia implements Serializable {

	private static final long serialVersionUID = 20190305L;

	@EmbeddedId
	private ColoniaCompositeKey compositeKey;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "fk_municipio", referencedColumnName = "id_municipio"),
			@JoinColumn(name = "fk_estado", referencedColumnName = "fk_estado") })
	private Municipio municipio;

}
