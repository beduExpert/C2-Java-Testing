package negocio;

import java.sql.SQLException;
import java.util.Date;

import modelo.HorarioEntrada;
import persistencia.HorarioEntradaDAO;

public class CalculadorDeRetardos {
	
	private HorarioEntradaDAO hedao;

	public int getRetardo(Date fechaLogueo) throws SQLException {
		int minutos;
		HorarioEntrada he = hedao.getUltimaFecha();
		minutos = getMinutosRetardo(getDiferenciaEnMilisegundos(fechaLogueo, he));
		return minutos;

	}

	private int getMinutosRetardo(long retardo) {
		return (int) retardo / 1000 / 60;
	}

	private long getDiferenciaEnMilisegundos(Date fechaLogueo, HorarioEntrada he) {
		long diferencia;
		diferencia = fechaLogueo.getTime() - he.getFechaInicio().getTime();
		if (diferencia < 0) {
			return 0;
		} else {
			return diferencia;
		}
	}

	protected void setHorariosEntradasDAO(HorarioEntradaDAO hedao) {
		this.hedao = hedao;
	}
}