/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "ruta_asignada")
@TableGenerator(name = "generador_ruta", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "ruta_asignada")
@Data
public class RutaAsignada implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_ruta")
	@Column(name = "id_ruta_asignada")
	private Long idRuta;

	@Column(name = "fecha_elaboracion")
	private LocalDate fechaElaboracion;

	@Column(name = "fecha")
	private LocalDate fecha;

	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	@JoinTable(name = "ruta_programada", joinColumns = {
			@JoinColumn(name = "fk_ruta_asignada", referencedColumnName = "id_ruta_asignada") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_establecimiento", referencedColumnName = "id_establecimiento") })
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Establecimiento> establecimiento;

}
