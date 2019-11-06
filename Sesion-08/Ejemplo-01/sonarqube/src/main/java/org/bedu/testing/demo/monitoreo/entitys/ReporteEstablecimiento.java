/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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

import org.bedu.testing.demo.monitoreo.enums.TipoReporte;

import lombok.Data;

/**
 *
 * @author cypunk
 */
@Entity
@Table(name = "reporte")
@TableGenerator(name = "generador_reporte", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "reporte")
@Data
public class ReporteEstablecimiento implements Serializable, Comparable<ReporteEstablecimiento> {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_reporte")
	@Column(name = "id_reporte")
	private Long idReporte;

	@Column(name = "fecha")
	private LocalDateTime fecha;

	@Column(name = "fecha_revision")
	private LocalDateTime fechaRevision;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_establecimiento")
	private Establecimiento establecimiento;

	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	@Column(name = "tipo_reporte")
	@Enumerated(EnumType.STRING)
	private TipoReporte tipoReporte;

	@Override
	public int compareTo(ReporteEstablecimiento o) {
		return fecha.compareTo(o.getFecha());
	}

}
