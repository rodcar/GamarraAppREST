package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Suscription;

public interface SuscriptionService extends CrudService<Suscription>{
	Optional<Suscription> findById(Long id) throws Exception;
}
