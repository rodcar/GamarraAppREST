package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Suscripcion;

public interface SuscripcionService extends CrudService<Suscripcion>{
	Optional<Suscripcion> findById(Long id) throws Exception;
}
