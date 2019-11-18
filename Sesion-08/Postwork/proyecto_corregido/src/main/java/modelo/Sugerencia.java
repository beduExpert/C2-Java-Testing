package modelo;

import java.io.Serializable;

public class Sugerencia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String comentario;
	private Usuario usuario;

	protected Sugerencia() {
		//Este constructor está intencionalmente vacío. 
	}

	public Sugerencia(String sugerencia, Usuario usuario) {
		this.comentario = sugerencia;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getSugerencia() {
		return comentario;
	}

	public void setSugerencia(String sugerencia) {
		this.comentario = sugerencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Sugerencia{" + "id=" + id + ", sugerencia=" + comentario + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final Sugerencia other = (Sugerencia) obj;
		
		return !((this.id != other.id) || this.comentario == null ? other.comentario != null: !this.comentario.equals(other.comentario));
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + this.id;
		hash = 53 * hash + (this.comentario != null ? this.comentario.hashCode() : 0);
		return hash;
	}
}