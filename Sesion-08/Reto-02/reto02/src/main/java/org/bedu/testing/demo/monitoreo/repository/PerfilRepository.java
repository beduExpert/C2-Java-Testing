/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bedu.testing.demo.monitoreo.repository;

import org.bedu.testing.demo.monitoreo.entitys.Perfil;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Fernando
 */
public interface PerfilRepository extends CrudRepository<Perfil, Long> {
    Perfil findByIdPerfil(Long idPerfil);
}
