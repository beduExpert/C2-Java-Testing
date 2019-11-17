package negocio;

import java.sql.SQLException;
import java.util.Date;

import modelo.HorarioEntrada;
import persistencia.HorarioEntradaDAO;

public class AperturaDeSistema {

	private HorarioEntradaDAO hedao;

	public boolean estaDisponible(Date horaAcceso) throws SQLException {
		HorarioEntrada he = hedao.getUltimaFecha();
		
		return horaAcceso.after(he.getFechaInicio());
	}

	protected void setHorariosEntradasDAO(HorarioEntradaDAO hedao) {
		this.hedao = hedao;
	}
}
