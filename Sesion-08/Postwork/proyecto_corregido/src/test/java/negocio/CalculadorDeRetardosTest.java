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

public class CalculadorDeRetardosTest {
	@Mock
	private HorarioEntradaDAO horariosEntradasDAOmock;

	@Mock
	private HorarioEntrada horariosEntradasmock;

	@InjectMocks
	private CalculadorDeRetardos calculadorDeRetardos;

	@BeforeEach
	void configurar() {
		MockitoAnnotations.initMocks(this);
		calculadorDeRetardos.setHorariosEntradasDAO(horariosEntradasDAOmock);
	}

	@Test
	public void pruebaRetardoDiezMinutos() {
		try {
			Mockito.when(horariosEntradasDAOmock.getUltimaFecha()).thenReturn(horariosEntradasmock);
			Mockito.when(horariosEntradasmock.getFechaInicio()).thenReturn(new Date(2011, 05, 25, 12, 0));

			assertEquals(10, calculadorDeRetardos.getRetardo(new Date(2011, 05, 25, 12, 10)));
		} catch (SQLException e) {
			fail(e);
		}
	}

	@Test
	public void pruebaRetardoLlegandoATiempo() {
		try {
			Mockito.when(horariosEntradasDAOmock.getUltimaFecha()).thenReturn(horariosEntradasmock);
			Mockito.when(horariosEntradasmock.getFechaInicio()).thenReturn(new Date(2011, 05, 25, 12, 0));

			assertEquals(0, calculadorDeRetardos.getRetardo(new Date(2011, 05, 25, 12, 0)));
		} catch (SQLException e) {
			fail(e);
		}
	}
}
