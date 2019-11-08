package org.bedu.retoBaseDatos.repository;

import org.bedu.retoBaseDatos.Pago;
import org.springframework.data.repository.CrudRepository;

public interface PagosRepository extends CrudRepository<Pago, Integer> {
    //Los métodos necesarios para el manejo de la base de datos se encuentran
    //implementados por el framework spring y son los siguientes:
    //delete, findAll, findById, save
}
