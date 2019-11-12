/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "bitacora_jornada")
@TableGenerator(name = "generador_bj", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "bitacora_jornada")
@Data
public class BitacoraJornada implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_bj")
	@Column(name = "id_bitacora_jornada")
	private Long idBitacoraJornada;

	@Column(name = "inicio_jornada")
	private LocalDateTime inicioJornada;

	@Column(name = "fin_jornada")
	private LocalDateTime finJornada;

	@Column(name = "kms_recorridos")
	private double kmsRecorridos;

	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;
}
