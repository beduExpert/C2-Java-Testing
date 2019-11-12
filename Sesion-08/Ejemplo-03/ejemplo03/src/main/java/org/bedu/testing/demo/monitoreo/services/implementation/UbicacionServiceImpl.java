package org.bedu.testing.demo.monitoreo.services.implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.bedu.testing.demo.monitoreo.entitys.Ubicacion;
import org.bedu.testing.demo.monitoreo.excepciones.MonitoreoException;
import org.bedu.testing.demo.monitoreo.repository.UbicacionRepository;
import org.bedu.testing.demo.monitoreo.services.UbicacionService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Secured("IS_AUTHENTICATED_FULLY")
public class UbicacionServiceImpl implements UbicacionService {

	private final UbicacionRepository repository;
	private final UsuarioService usuarioService;

	@Override
	public Ubicacion getLocation(Long idPromotor) {
		Optional<Ubicacion> ubicacionPromotor = repository.findById(idPromotor);

		if (ubicacionPromotor.isPresent()) {
			return ubicacionPromotor.get();
		} else {
			String message = String.format("No hay registro de ubicación para el promotor [%d]", idPromotor);
			log.error(message);
			throw new MonitoreoException(message);
		}
	}

	@Override
	public void saveLocation(BigDecimal latitud, BigDecimal longitud) {
		Long id = usuarioService.getLoggedUser().getIdUsuario();
		log.debug("Saving location ({},{}) for user {}", latitud, longitud, id);
		repository.save(new Ubicacion(id, latitud, longitud, LocalDateTime.now()));

	}

}
