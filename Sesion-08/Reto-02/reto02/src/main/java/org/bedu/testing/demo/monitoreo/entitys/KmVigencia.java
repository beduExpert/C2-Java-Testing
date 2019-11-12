/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "km_vigencia")
@TableGenerator(name = "generador_kmv", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "km_vigencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KmVigencia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_kmv")
	@Column(name = "id_km_vigencia")
	private Long idKmVigencia;

	@Column(name = "fecha_vigencia")
	private LocalDate fechaVigencia;

	@Column(name = "km_establecimientos")
	private double valorKilometros;
}
