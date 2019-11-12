/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.bedu.testing.demo.monitoreo.enums.Estatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "establecimiento")
@TableGenerator(name = "generador_est", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "establecimiento")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Establecimiento implements Serializable {
	private static final long serialVersionUID = 20190305L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_est")
	@Column(name = "id_establecimiento")
	private Long idEstablecimiento;

	@Column(name = "nombre_establecimiento")
	private String nombreEstablecimiento;

	@Column(name = "calle_y_numero")
	private String calleYNumero;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "correo")
	private String correo;

	@Column(name = "contacto")
	private String contacto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_fotografia")
	@JsonIgnore
	private Fotografia fotografia;

	@ManyToOne
	@JoinColumn(name = "fk_tipo_establecimiento")
	private TipoEstablecimiento tipoEstablecimiento;

	@ManyToOne
	@JoinColumn(name = "fk_zona")
	private Zona zona;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "fk_colonia", referencedColumnName = "id_colonia"),
			@JoinColumn(name = "codigo_postal", referencedColumnName = "codigo_postal") })
	private Colonia colonia;

	@Column(name = "estatus")
	@Enumerated(EnumType.STRING)
	private Estatus estatus;

	@JsonIgnore
	@ManyToMany(mappedBy = "establecimiento")
	private List<RutaAsignada> rutaAsignada;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_establecimiento")
	private List<Visita> visitas;

	@Column(name = "latitud")
	BigDecimal latitud;

	@Column(name = "longitud")
	BigDecimal longitud;

}
