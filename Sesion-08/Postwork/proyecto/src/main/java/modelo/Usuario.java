package modelo;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String password;
	private String nombreCompleto;
	private boolean administrador;
	private Castigo castigo;
	private byte[] foto;

	private List<Asistencia> historial;

	protected Usuario() {
		//Este constructor está intencionalmente vacío. 
	}

	public Usuario(String email, String password, String nombreCompleto) {
		this.email = email;
		this.password = password;
		this.nombreCompleto = nombreCompleto;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public Castigo getCastigo() {
		return castigo;
	}

	public void setCastigo(Castigo castigo) {
		this.castigo = castigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Asistencia> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Asistencia> historial) {
		this.historial = historial;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario{" + "id=" + id + ", email=" + email + ", nombreCompleto=" + nombreCompleto + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null  || getClass() != obj.getClass()) {
			return false;
		}
		
		final Usuario other = (Usuario) obj;
		
		return !((this.id != other.id) || this.email == null ? other.email != null : !this.email.equals(other.email));
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 53 * hash + this.id;
		hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
		return hash;
	}
}
