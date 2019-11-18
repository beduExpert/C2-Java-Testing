package persistencia;

import java.sql.SQLException;

import modelo.HorarioEntrada;

public interface HorarioEntradaDAO {
	HorarioEntrada getUltimaFecha() throws SQLException;

	void saveStartDateTime(HorarioEntrada horariosEntradas) throws SQLException;
}
