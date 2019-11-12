/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "cuestionario")
@TableGenerator(name = "generador_cuest", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "cuestionario")
@Data
public class Cuestionario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_cuest")
	@Column(name = "id_cuestionario")
	private Long idCuestionario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_establecimiento")
	private Establecimiento establecimiento;

	@Column(name = "antiguedad_meses")
	private int antiguedadMeses;

	@ManyToOne
	@JoinColumn(name = "fk_tipo_establecimiento")
	private TipoEstablecimiento tipoEstablecimiento;

	@Column(name = "solo_papeleria")
	private int soloPapeleria;

	@Column(name = "surte_negocio")
	private String surteNegocio;

	@Column(name = "conoce_scool")
	private int conoceScool;

	@Column(name = "comprado_en_marchand")
	private int compradoEnMarchand;

	@Column(name = "opinion")
	private String opinion;
}
