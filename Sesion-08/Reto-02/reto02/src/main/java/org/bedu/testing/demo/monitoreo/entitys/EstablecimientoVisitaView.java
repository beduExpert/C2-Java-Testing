package org.bedu.testing.demo.monitoreo.entitys;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bedu.testing.demo.monitoreo.enums.Estatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ultima_visita")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstablecimientoVisitaView {

	@Id
	@Column(name = "id_establecimiento")
	private Long idEstablecimiento;

	@Column(name = "nombre_establecimiento")
	private String nombreEstablecimiento;

	@Column(name = "calle_y_numero")
	private String calleYNumero;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "correo")
	private String correo;

	@Column(name = "contacto")
	private String contacto;

	@OneToOne
	@JoinColumn(name = "fk_fotografia")
	private Fotografia fotografia;

	@ManyToOne
	@JoinColumn(name = "fk_tipo_establecimiento")
	private TipoEstablecimiento tipoEstablecimiento;

	@ManyToOne
	@JoinColumn(name = "fk_zona")
	private Zona zona;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "fk_colonia", referencedColumnName = "id_colonia"),
			@JoinColumn(name = "codigo_postal", referencedColumnName = "codigo_postal") })
	private Colonia colonia;

	@Column(name = "estatus")
	@Enumerated(EnumType.STRING)
	private Estatus estatus;

	@OneToOne
	@JoinColumn(name = "fk_ultima_visita")
	private Visita ultimaVisita;

	@Column(name = "latitud")
	BigDecimal latitud;

	@Column(name = "longitud")
	BigDecimal longitud;

}
