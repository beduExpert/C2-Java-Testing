package org.bedu.testing.demo.monitoreo.services.implementation;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.InformacionPostal;
import org.bedu.testing.demo.monitoreo.repository.ColoniaRepository;
import org.bedu.testing.demo.monitoreo.services.CodigoPostalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class CodigoPostalServiceImpl implements CodigoPostalService {

	private final ColoniaRepository repository;

	@Override
	public InformacionPostal informacionPostal(String codigoPostal) {

		List<Colonia> colonias = repository.findAllByCompositeKeyCodigoPostal(codigoPostal);

		if (colonias.isEmpty()) {
			return new InformacionPostal(codigoPostal, null, null, null);
		}

		Colonia c = colonias.get(0);
		String municipioNombre = c.getMunicipio().getDescripcion();
		String estadoNombre = c.getMunicipio().getEstado().getDescripcion();

		return new InformacionPostal(codigoPostal, estadoNombre, municipioNombre, colonias);

	}

}
