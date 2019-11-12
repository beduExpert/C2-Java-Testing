/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.Collections;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.EstablecimientoDTO;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.repository.MensajeRepository;
import org.bedu.testing.demo.monitoreo.services.MensajeService;
import org.bedu.testing.demo.monitoreo.tools.EstablecimientoEstablecimientoDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cypunk
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class MensajeServiceImplement implements MensajeService {
	private final MensajeRepository mensajeReporsitory;
	private final EstablecimientoEstablecimientoDTOConverter converter;

	@Override
	public Mensaje cambiarEstatusMensaje(Long idMensaje) {
		Mensaje mensaje = mensajeReporsitory.findByIdMensaje(idMensaje);
		mensaje.setLeido(!mensaje.isLeido());

		log.debug("Actualizando estatus de mensaje [{}]. Nuevo estatus [{}]", idMensaje, mensaje.isLeido());

		Mensaje savedMessage = mensajeReporsitory.save(mensaje);

		log.debug("Respuesta enviada [{}]", savedMessage);

		return savedMessage;
	}

	@Override
	public List<Mensaje> obtenerMensajesPorEstablecimiento(Establecimiento establecimiento) {
		List<Mensaje> mensajes = mensajeReporsitory.findByEstablecimiento(establecimiento);
		Collections.reverse(mensajes);
		return mensajes;
	}

	@Override
	public List<Mensaje> obtenerMensajesPorEstablecimientoYEstatus(Establecimiento establecimiento, boolean estatus) {
		return mensajeReporsitory.findByEstablecimientoAndLeido(establecimiento, estatus);
	}

	@Override
	public List<Mensaje> obtenerMensajesPorUsuario(Usuario usuario) {
		return mensajeReporsitory.findByUsuario(usuario);
	}

	@Override
	public Page<Mensaje> obtenertodosLosMensajes(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return mensajeReporsitory.findAll(pageRequest);
	}

	@Override
	public Page<Mensaje> obtenertodosLosMensajesPorEstatus(boolean estatus, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return mensajeReporsitory.findByLeido(estatus, pageRequest);
	}

	@Override
	public Page<EstablecimientoDTO> obtenerEstablecimientosConMensajesActivosPorNombre(String nombre, int page,
			int size) {
		PageRequest pr = PageRequest.of(page, size);
		return converter.convert(
				mensajeReporsitory.findEstablecimientosWithMessagesAndByName(String.format("%%%s%%", nombre), pr));
	}

}
