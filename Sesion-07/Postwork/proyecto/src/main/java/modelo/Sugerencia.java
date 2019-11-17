package modelo;

import java.io.Serializable;

public class Sugerencia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String sugerencia;
	private Usuario usuario;

	protected Sugerencia() {
		//Este constructor está intencionalmente vacío. 
	}

	public Sugerencia(String sugerencia, Usuario usuario) {
		this.sugerencia = sugerencia;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Sugerencia{" + "id=" + id + ", sugerencia=" + sugerencia + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final Sugerencia other = (Sugerencia) obj;
		
		return !((this.id != other.id) || this.sugerencia == null ? other.sugerencia != null: !this.sugerencia.equals(other.sugerencia));
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + this.id;
		hash = 53 * hash + (this.sugerencia != null ? this.sugerencia.hashCode() : 0);
		return hash;
	}
}