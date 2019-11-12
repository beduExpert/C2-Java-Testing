/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Establecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.MensajeService;
import org.bedu.testing.demo.monitoreo.services.SaveMensajeService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author cypunk
 */
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@RequestMapping(value = "/mensaje", produces = APPLICATION_JSON_VALUE)
@RestController
public class MensajeController {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

	@Data
	private static class MensajeInputDTO {
		String mensaje;
		Long idEstablecimiento;
	}

	@Data
	@AllArgsConstructor
	private static class MensajeOutputDTO {

		Long id;
		String mensaje;
		String usuario;
		String perfil;
		String fecha;
		boolean leido;

	}

	private final MensajeService mensajeService;
	private final SaveMensajeService saveMensajeService;
	private final UsuarioService usuarioService;
	private final EstablecimientoService establecimientoService;

	@PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE)
	public Mensaje enviarMensaje(@RequestBody MensajeInputDTO mensaje) {
		return saveMensajeService.nuevoMensaje(mensaje.mensaje, mensaje.idEstablecimiento);
	}

	@PutMapping(value = "/{idMensaje}")
	public Mensaje cambiarEstatusMensaje(@PathVariable Long idMensaje) {
		return mensajeService.cambiarEstatusMensaje(idMensaje);
	}

	@GetMapping("/usuario/{idUsuario}")
	public List<Mensaje> obtenerMensajesPorUsuario(@PathVariable Long idUsuario) {
		Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
		return mensajeService.obtenerMensajesPorUsuario(usuario);
	}

	@GetMapping("/")
	public Page<Mensaje> obtenerTodosLosMensajes(@RequestParam int size, @RequestParam int page) {
		return mensajeService.obtenertodosLosMensajes(page, size);
	}

	@GetMapping("/estatus/{estatus}")
	public Page<Mensaje> obtenerMensajesPorEstatus(@PathVariable boolean estatus, @RequestParam int size,
			@RequestParam int page) {

		return mensajeService.obtenertodosLosMensajesPorEstatus(estatus, page, size);
	}

	@GetMapping("/establecimiento/{idEstablecimiento}")
	public List<MensajeOutputDTO> obtenerMensajesPorEstablecimientos(@PathVariable Long idEstablecimiento) {
		return formatearMensajes(obtenerMensajes(idEstablecimiento));

	}

	private List<MensajeOutputDTO> formatearMensajes(List<Mensaje> mensajes) {
		List<MensajeOutputDTO> mensajesFormateados = new ArrayList<>();

		for (Mensaje m : mensajes) {
			String nombre = String.format("%s %s", m.getUsuario().getNombre(), m.getUsuario().getApellidoPaterno());
			String perfil = m.getUsuario().getPerfil().getDescripcion();
			MensajeOutputDTO mf = new MensajeOutputDTO(m.getIdMensaje(), m.getMensaje(), nombre, perfil,
					m.getFecha().format(FORMATTER), m.isLeido());
			mensajesFormateados.add(mf);
		}
		return mensajesFormateados;
	}

	private List<Mensaje> obtenerMensajes(Long idEstablecimiento) {
		Establecimiento establecimiento = establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento);

		return mensajeService.obtenerMensajesPorEstablecimiento(establecimiento);
	}

	@GetMapping("/{idEstablecimiento}/{estatus}")
	public List<Mensaje> obtenerMensajesPorEstablecimientosYEstatus(@PathVariable Long idEstablecimiento,
			@PathVariable boolean estatus) {
		Establecimiento establecimiento = establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento);
		return mensajeService.obtenerMensajesPorEstablecimientoYEstatus(establecimiento, estatus);
	}

}
