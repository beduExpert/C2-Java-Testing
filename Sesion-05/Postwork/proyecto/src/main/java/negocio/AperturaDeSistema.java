package negocio;

import java.sql.SQLException;
import java.util.Date;

import modelo.HorarioEntrada;
import persistencia.HorarioEntradaDAO;

public class AperturaDeSistema {

	private HorarioEntradaDAO hedao;

	public boolean estaDisponible(Date HoraAcceso) throws SQLException {
		HorarioEntrada he = hedao.getUltimaFecha();
		if (HoraAcceso.after(he.getFechaInicio())) {
			return true;
		}
		return false;
	}

	protected void setHorariosEntradasDAO(HorarioEntradaDAO hedao) {
		this.hedao = hedao;
	}
}
