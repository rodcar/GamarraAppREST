package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Prenda;

public interface PrendaService extends CrudService<Prenda>{
	Optional<Prenda> findById(Long id) throws Exception;
}
