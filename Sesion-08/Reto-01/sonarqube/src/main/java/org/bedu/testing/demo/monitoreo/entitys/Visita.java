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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

import org.bedu.testing.demo.monitoreo.enums.ResultadoVisita;

import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "visita")
@TableGenerator(name = "generador_visita", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 20, pkColumnValue = "visita")
@Data
public class Visita implements Serializable, Comparable<Visita> {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_visita")
	@Column(name = "id_visita")
	private Long idVisita;

	@Column(name = "fecha")
	private LocalDateTime fecha;

	@Column(name = "comentario")
	@Size(max = 500)
	private String comentario;

	@Column(name = "resultado_visita")
	@Enumerated(EnumType.STRING)
	private ResultadoVisita resultado;

	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	@Override
	public int compareTo(Visita other) {
		return this.fecha.compareTo(other.getFecha());
	}

}
