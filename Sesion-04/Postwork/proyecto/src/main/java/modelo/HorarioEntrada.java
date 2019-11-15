package modelo;

import java.io.Serializable;
import java.util.Date;

public class HorarioEntrada implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fechaInicio;

	protected HorarioEntrada() {
	}

	public HorarioEntrada(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public String toString() {
		return "HorariosEntradas{" + "fechaInicio=" + fechaInicio + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final HorarioEntrada other = (HorarioEntrada) obj;
		if (this.fechaInicio != other.fechaInicio
				&& (this.fechaInicio == null || !this.fechaInicio.equals(other.fechaInicio))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + (this.fechaInicio != null ? this.fechaInicio.hashCode() : 0);
		return hash;
	}
}