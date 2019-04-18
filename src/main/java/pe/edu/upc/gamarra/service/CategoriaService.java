package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Categoria;

public interface CategoriaService extends CrudService<Categoria>{
	Optional<Categoria> findById(Long id) throws Exception;
}
