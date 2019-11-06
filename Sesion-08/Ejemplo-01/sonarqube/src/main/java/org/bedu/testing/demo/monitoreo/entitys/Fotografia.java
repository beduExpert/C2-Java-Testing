package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;

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

@Entity
@Table(name = "fotografia")
@TableGenerator(name = "generador_fot", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "fotografia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fotografia implements Serializable {

	@Id
	@GeneratedValue(generator = "generador_fot", strategy = GenerationType.TABLE)
	@Column(name = "id")
	private Long id;

	@Column(name = "binario")
	private String binario;

}
