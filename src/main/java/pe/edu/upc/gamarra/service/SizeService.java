package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Size;

public interface SizeService extends CrudService<Size>{
	Optional<Size> findById(Long id) throws Exception;
}
