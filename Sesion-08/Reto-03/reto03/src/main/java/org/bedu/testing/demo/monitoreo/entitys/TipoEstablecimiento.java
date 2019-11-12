/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "tipo_establecimiento")
@Data
public class TipoEstablecimiento implements Serializable {
	@Id
	@Column(name = "id_tipo_establecimiento")
	private Long idTipoEstablecimiento;

	@Column(name = "descripcion")
	private String descripcion;
}
