package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Marcador;

public interface MarcadorService extends CrudService<Marcador>{
	Optional<Marcador> findById(Long id) throws Exception;
}
