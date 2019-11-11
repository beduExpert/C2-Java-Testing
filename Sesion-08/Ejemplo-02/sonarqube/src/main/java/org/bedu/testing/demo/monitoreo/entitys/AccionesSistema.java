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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.TableGenerator;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "acciones_sistema")
@TableGenerator(name = "generador_as", table = "id_generator",
        pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1,
        pkColumnValue = "acciones_sistema")
@Data
public class AccionesSistema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generador_as")
    @Column(name = "id_acciones_sistema")
    private Long idAccionesSistema;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="fecha")
    private LocalDateTime fecha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}
