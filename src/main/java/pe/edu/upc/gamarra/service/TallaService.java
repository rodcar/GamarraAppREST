package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Talla;

public interface TallaService extends CrudService<Talla>{
	Optional<Talla> findById(Long id) throws Exception;
}
