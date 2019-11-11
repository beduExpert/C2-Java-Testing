/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import java.util.List;

import org.bedu.testing.demo.monitoreo.entitys.Colonia;
import org.bedu.testing.demo.monitoreo.entitys.Municipio;
import org.bedu.testing.demo.monitoreo.entitys.keys.ColoniaCompositeKey;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Fernando
 */
public interface ColoniaRepository extends CrudRepository<Colonia, ColoniaCompositeKey> {
	List<Colonia> findByMunicipio(Municipio delegacion);

	List<Colonia> findAllByCompositeKeyCodigoPostal(String codigoPostal);
}
