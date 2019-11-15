package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import modelo.Asistencia;
import modelo.Castigo;
import modelo.Usuario;

public class AsignadorDeCastigos {
	private int numeroDeCastigados;
	private String diaInicio;
	private List<Castigo> listaCastigos;
	private List<Usuario> listaUsuarios;
	private List<Usuario> listaCastigados;

	public AsignadorDeCastigos(List<Castigo> listaCastigos, List<Usuario> listaUsuarios) {
		leerPropiedades();
		this.listaCastigos = listaCastigos;
		this.listaUsuarios = listaUsuarios;
		this.listaCastigados = null;
	}

	private int cuentaCastigosDisponibles() {
		int cuenta = 0;

		for (Castigo c : listaCastigos) {
			cuenta = c.isDisponible() && c.getPrioridad() > -1 ? cuenta + 1 : cuenta;
		}

		return cuenta;
	}

	private void leerPropiedades() throws NumberFormatException {
		ResourceBundle propiedades = ResourceBundle.getBundle(this.getClass().getSimpleName());

		diaInicio = propiedades.getString("dia-inicio");
		numeroDeCastigados = Integer.parseInt(propiedades.getString("numero-de-castigados").trim());
	}

	private void recortarLista() {
		listaCastigos = listaCastigos.subList(0, numeroDeCastigados);
	}

	private void verificarCastigosSuficientes() throws SQLException {
		int disponibles = cuentaCastigosDisponibles();

		if (disponibles < numeroDeCastigados) {
			for (Castigo c : listaCastigos) {
				c.setDisponible(true);
			}
		}
	}

	public void asignarCastigos() {
		try {
			verificarCastigosSuficientes();
			desordenar();
			ordenarCastigosPorPrioridad();
			recortarLista();
			ordenarCastigosPorPrecio();
			ordenarListaUsuarios();
			ponerCastigos();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void desordenar() {
		Collections.shuffle(listaCastigos);
	}

	private void ordenarCastigosPorPrioridad() {
		Collections.sort(listaCastigos, new Comparator<Castigo>() {
			public int compare(Castigo o1, Castigo o2) {
				return o2.getPrioridad() - o1.getPrioridad();
			}
		});
	}

	private void ordenarCastigosPorPrecio() {
		Collections.sort(listaCastigos, new Comparator<Castigo>() {
			public int compare(Castigo o1, Castigo o2) {
				return (int) ((o2.getPrecio() - o1.getPrecio()) * 100);
			}
		});

	}

	private void ordenarListaUsuarios() {
		Collections.sort(listaUsuarios, new Comparator<Usuario>() {
			public int compare(Usuario o1, Usuario o2) {
				int acumulado1 = calcularAcumulado(o1);
				int acumulado2 = calcularAcumulado(o2);

				return acumulado2 - acumulado1;
			}

			private int calcularAcumulado(Usuario u) {
				int acumulado = 0;
				List<Asistencia> historial = new ArrayList<Asistencia>(u.getHistorial());
				Collections.reverse(historial);

				for (Asistencia a : historial) {
					acumulado += a.getMinutosRetrazo();
					if (a.getFechaLogueo().toString().contains(diaInicio)) {
						break;
					}
				}

				return acumulado;
			}
		});
	}

	public List<Castigo> getListaCastigos() {
		return listaCastigos;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public int getNumeroDeCastigados() {
		return numeroDeCastigados;
	}

	List<Usuario> getListaCastigados() {
		return listaCastigados;
	}

	private void ponerCastigos() {
		listaCastigados = new ArrayList<Usuario>();
		Usuario u;
		Castigo c;

		for (int i = 0; i < numeroDeCastigados; i++) {
			u = listaUsuarios.get(i);
			c = listaCastigos.get(i);
			u.setCastigo(c);
			c.setDisponible(false);
			listaCastigados.add(u);
		}
	}

	public String getDiaInicio() {
		return diaInicio;
	}
}
