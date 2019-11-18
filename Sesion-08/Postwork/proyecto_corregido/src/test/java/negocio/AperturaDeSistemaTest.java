package negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import modelo.HorarioEntrada;
import persistencia.HorarioEntradaDAO;

public class AperturaDeSistemaTest {

	@Mock
	private HorarioEntradaDAO horariosEntradasDAOmock;

	@Mock
	private HorarioEntrada horariosEntradasmock;

	@InjectMocks
	private AperturaDeSistema aperturaDeSistema;

	@BeforeEach
	void configurar() {
		MockitoAnnotations.initMocks(this);
		aperturaDeSistema.setHorariosEntradasDAO(horariosEntradasDAOmock);
	}

	@Test
	public void pruebaAperturaFallida() {
		try {
			Mockito.when(horariosEntradasDAOmock.getUltimaFecha()).thenReturn(horariosEntradasmock);
			Mockito.when(horariosEntradasmock.getFechaInicio()).thenReturn(new Date(2011, 05, 28, 02, 44));

			assertEquals(false, aperturaDeSistema.estaDisponible(new Date(2011, 05, 28, 02, 40)));
		} catch (SQLException e) {
			fail(e);
		}
	}

	@Test
	public void pruebaAperturaExistosa() {
		try {
			Mockito.when(horariosEntradasDAOmock.getUltimaFecha()).thenReturn(horariosEntradasmock);
			Mockito.when(horariosEntradasmock.getFechaInicio()).thenReturn(new Date(2011, 05, 28, 14, 44));

			assertEquals(true, aperturaDeSistema.estaDisponible(new Date(2011, 05, 28, 14, 50)));
		} catch (SQLException e) {
			fail(e);
		}

	}

}
