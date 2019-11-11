package org.bedu.testing.demo.monitoreo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MonitoreoException extends RuntimeException {

	public MonitoreoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MonitoreoException(String arg0) {
		super(arg0);
	}

}
