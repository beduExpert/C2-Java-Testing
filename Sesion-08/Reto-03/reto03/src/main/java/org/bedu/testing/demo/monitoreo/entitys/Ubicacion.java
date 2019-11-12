package org.bedu.testing.demo.monitoreo.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ubicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion implements Serializable {

	private static final long serialVersionUID = 20190115L;

	@Id
	private Long id;

	private BigDecimal latitud;
	private BigDecimal longitud;

	@Column(name = "hora")
	private LocalDateTime timestamp;

}
