/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "perfil")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfil implements Serializable {

	public static final long PROMOTOR_ID = 4L;
	public static final long SUPERADMIN_ID = 3L;
	public static final long OPERATIVO_ID = 2L;
	public static final long ADMIN_ID = 1L;

	@Id
	@Column(name = "id_perfil")
	private Long idPerfil;

	@Column(name = "descripcion")
	private String descripcion;
}
