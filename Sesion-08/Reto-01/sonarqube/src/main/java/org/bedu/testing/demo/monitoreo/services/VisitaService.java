/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services;

import java.time.LocalDate;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.RutaAsignada;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.entitys.Visita;
import org.bedu.testing.demo.monitoreo.enums.ResultadoVisita;

/**
 *
 * @author Fernando
 */
public interface VisitaService {

	Visita agregarVisita(Long idEstablecimiento, String comentario, ResultadoVisita resultado);

	List<Visita> obtenerPorPromotorYFecha(Usuario usuario, LocalDate fecha);

	RutaAsignada visitasDeUsuarioDelDia(long idPromotor);

}
