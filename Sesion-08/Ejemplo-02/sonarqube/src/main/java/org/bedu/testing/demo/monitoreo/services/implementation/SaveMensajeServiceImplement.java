package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDateTime;

import org.bedu.testing.demo.monitoreo.entitys.Mensaje;
import org.bedu.testing.demo.monitoreo.repository.MensajeRepository;
import org.bedu.testing.demo.monitoreo.services.EstablecimientoService;
import org.bedu.testing.demo.monitoreo.services.SaveMensajeService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class SaveMensajeServiceImplement implements SaveMensajeService {

	private final MensajeRepository mensajeReporsitory;
	private final EstablecimientoService establecimientoService;
	private final UsuarioService usuarioService;

	@Override
	public Mensaje nuevoMensaje(String mensaje, Long idEstablecimiento) {

		Mensaje mensajeObj = buildMessage(mensaje, idEstablecimiento);

		return mensajeReporsitory.save(mensajeObj);
	}

	private Mensaje buildMessage(String mensaje, Long idEstablecimiento) {
		Mensaje mensajeObj = new Mensaje();
		mensajeObj.setEstablecimiento(establecimientoService.obtenerEstablecimientoPorId(idEstablecimiento));
		mensajeObj.setFecha(LocalDateTime.now());
		mensajeObj.setLeido(false);
		mensajeObj.setMensaje(mensaje);
		mensajeObj.setUsuario(usuarioService.getLoggedUser());
		return mensajeObj;
	}
}
