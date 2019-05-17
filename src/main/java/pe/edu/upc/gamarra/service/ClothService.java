package pe.edu.upc.gamarra.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.gamarra.entities.Category;
import pe.edu.upc.gamarra.entities.Cloth;

public interface ClothService extends CrudService<Cloth>{
	Optional<Cloth> findById(Long id) throws Exception;

	List<Cloth> findByCategoryId(Category category);
	
	List<Cloth> findByNameContaining(String name);
}
