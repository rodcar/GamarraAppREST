package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Gallery;

public interface GalleryService extends CrudService<Gallery>{
	Optional<Gallery> findById(Long id) throws Exception;
}
