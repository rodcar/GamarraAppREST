package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Galeria;

public interface GaleriaService extends CrudService<Galeria>{
	Optional<Galeria> findById(Long id) throws Exception;
}
