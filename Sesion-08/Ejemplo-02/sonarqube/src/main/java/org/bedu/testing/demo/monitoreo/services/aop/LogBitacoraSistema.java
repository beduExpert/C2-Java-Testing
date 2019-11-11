/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.services.aop;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.bedu.testing.demo.monitoreo.entitys.AccionesSistema;
import org.bedu.testing.demo.monitoreo.entitys.ReporteEstablecimiento;
import org.bedu.testing.demo.monitoreo.entitys.Usuario;
import org.bedu.testing.demo.monitoreo.services.AccionesSistemaService;
import org.bedu.testing.demo.monitoreo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author cypunk
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class LogBitacoraSistema {
	private static final String APPROVE_TEMPLATE = "El usuario %s aprobó el reporte de tipo %s del establecimiento con id %s";
	private static final String REJECT_TEMPLATE = "El usuario %s descartó el reporte de tipo %s del establecimiento con id %s";
	private static final String ASIGN_TEMPLATE = "El usuario %s asignó la ruta para la fecha %s al usuario con id %s";

	private final AccionesSistemaService accionesSistemaService;
	private final UsuarioService usuarioService;

	@AfterReturning(value = "execution(* com.mozcalti.rocketdelivery.marchand.monitoreo.services.ReporteEstablecimientoService.aprobarReporte(..))")
	public void aprobacionDeReporteEstablecimiento(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		ReporteEstablecimiento reporte = (ReporteEstablecimiento) args[0];
		Usuario usuario = usuarioService.obtenerUsuarioPorId((Long) args[1]);

		AccionesSistema accionesSistema = new AccionesSistema();

		accionesSistema.setDescripcion(String.format(APPROVE_TEMPLATE, usuario.getUsername(), reporte.getTipoReporte(),
				reporte.getEstablecimiento().getIdEstablecimiento()));
		accionesSistema.setUsuario(usuario);
		accionesSistema.setFecha(LocalDateTime.now());

		accionesSistemaService.agregarRegistro(accionesSistema);
	}

	@AfterReturning(value = "execution(* com.mozcalti.rocketdelivery.marchand.monitoreo.services.ReporteEstablecimientoService.rechazaReporte(..))")
	public void rechazoDeReporteEstablecimiento(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		ReporteEstablecimiento reporte = (ReporteEstablecimiento) args[0];
		Usuario usuario = usuarioService.obtenerUsuarioPorId((Long) args[1]);

		AccionesSistema accionesSistema = new AccionesSistema();
		accionesSistema.setDescripcion(String.format(REJECT_TEMPLATE, usuario.getUsername(), reporte.getTipoReporte(),
				reporte.getEstablecimiento().getIdEstablecimiento()));
		accionesSistema.setUsuario(usuario);
		accionesSistema.setFecha(LocalDateTime.now());

		accionesSistemaService.agregarRegistro(accionesSistema);
	}

	@AfterReturning(value = "execution(* com.mozcalti.rocketdelivery.marchand.monitoreo.services.RutaAsignadaService.agregarNuevaRutaAsignada(..))")
	public void asignacionRutaAPromotor(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

		Usuario usuarioLogeado = usuarioService.obtenerUsuarioPorId((Long) args[2]);
		Usuario promotor = usuarioService.obtenerUsuarioPorId((Long) args[1]);

		AccionesSistema accionesSistema = new AccionesSistema();

		accionesSistema.setDescripcion(
				String.format(ASIGN_TEMPLATE, usuarioLogeado.getUsername(), args[0], promotor.getIdUsuario()));
		accionesSistema.setUsuario(usuarioLogeado);
		accionesSistema.setFecha(LocalDateTime.now());

		accionesSistemaService.agregarRegistro(accionesSistema);
	}
}
