package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Category;

public interface CategoryService extends CrudService<Category>{
	Optional<Category> findById(Long id) throws Exception;
}
