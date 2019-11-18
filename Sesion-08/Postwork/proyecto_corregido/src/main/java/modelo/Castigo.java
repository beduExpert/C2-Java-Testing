package modelo;

import java.io.Serializable;

public class Castigo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String descripcion;
	private boolean disponible;
	private float precio;
	private int prioridad;

	protected Castigo() {
		// Este constructor está intencionalmente vacío.
	}

	public Castigo(String descripcion, boolean disponible, float precio, int prioridad) {
		this.descripcion = descripcion;
		this.disponible = disponible;
		this.precio = precio;
		this.prioridad = prioridad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public String toString() {
		return "Castigo{" + "id=" + id + ", descripcion=" + descripcion + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		final Castigo other = (Castigo) obj;
		
		return (this.id == other.id) || this.descripcion == null  ? other.descripcion == null : this.descripcion.equals(other.descripcion);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
		hash = 73 * hash + (this.descripcion != null ? this.descripcion.hashCode() : 0);
		return hash;
	}
}
