package org.bedu.retoBaseDatos.service;

import org.bedu.retoBaseDatos.Pago;
import org.bedu.retoBaseDatos.repository.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PagosService {
    private final PagosRepository repositorio;

    public PagosService(@Autowired PagosRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Iterable<Pago> buscarTodos(){
        return repositorio.findAll();
    }

    public Pago buscarPorId(int id){
        return repositorio.findById(id).orElse(null);
    }

    public Pago editar(Pago pago){
        return repositorio.save(pago);
    }

    public void eliminar(int id){
        repositorio.deleteById(id);
    }
}
