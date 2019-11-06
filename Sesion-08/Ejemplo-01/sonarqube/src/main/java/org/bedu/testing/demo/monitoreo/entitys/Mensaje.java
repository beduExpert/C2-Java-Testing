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
 * @author cypunk
 */
@Entity
@Table(name = "mensaje")
@TableGenerator(name = "generador_mensaje", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "mensaje")
@Data
public class Mensaje implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_mensaje")
	@Column(name = "id_mensaje")
	private Long idMensaje;

	@Column(name = "mensaje")
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "fk_establecimiento")
	private Establecimiento establecimiento;

	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	@Column(name = "fecha")
	private LocalDateTime fecha;

	@Column(name = "estatus")
	private boolean leido;

}
