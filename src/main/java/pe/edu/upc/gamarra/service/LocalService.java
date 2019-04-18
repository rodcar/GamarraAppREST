package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Local;

public interface LocalService extends CrudService<Local>{
	Optional<Local> findById(Long id) throws Exception;
}
