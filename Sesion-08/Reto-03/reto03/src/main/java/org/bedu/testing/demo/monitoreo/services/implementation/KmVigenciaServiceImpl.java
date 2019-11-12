package org.bedu.testing.demo.monitoreo.services.implementation;

import java.time.LocalDate;

import org.bedu.testing.demo.monitoreo.entitys.KmVigencia;
import org.bedu.testing.demo.monitoreo.repository.KmVigenciaRepository;
import org.bedu.testing.demo.monitoreo.services.KmVigenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Secured("IS_AUTHENTICATED_FULLY")
public class KmVigenciaServiceImpl implements KmVigenciaService {

	private final KmVigenciaRepository repository;

	@Override
	public KmVigencia activeValue() {
		return activeValue(LocalDate.now());
	}

	@Override
	public KmVigencia activeValue(LocalDate date) {
		return repository.findActiveFare(date);
	}

}
