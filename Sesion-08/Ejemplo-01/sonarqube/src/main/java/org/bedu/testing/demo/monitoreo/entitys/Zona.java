/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "zona")
@TableGenerator(name = "generador_zona", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "zona")
@Data
public class Zona implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_zona")
	@Column(name = "id_zona")
	private Long idZona;

	@Column(name = "descripcion")
	private String descripcion;

}
