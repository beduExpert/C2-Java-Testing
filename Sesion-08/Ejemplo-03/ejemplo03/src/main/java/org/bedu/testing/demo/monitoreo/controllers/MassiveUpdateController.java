package org.bedu.testing.demo.monitoreo.controllers;

import org.bedu.testing.demo.monitoreo.services.GeolocationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MassiveUpdateController {

	private final GeolocationUpdateService updateService;

	@PutMapping(value = "/update")
	public void update(@RequestParam("force") boolean force) {
		updateService.updateGeolocation(force);
	}

}
