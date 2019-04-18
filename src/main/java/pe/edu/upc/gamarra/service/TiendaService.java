package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Tienda;

public interface TiendaService extends CrudService<Tienda>{
	Optional<Tienda> findById(Long id) throws Exception;
}
