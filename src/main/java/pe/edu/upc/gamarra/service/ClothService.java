package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Cloth;

public interface ClothService extends CrudService<Cloth>{
	Optional<Cloth> findById(Long id) throws Exception;
}
