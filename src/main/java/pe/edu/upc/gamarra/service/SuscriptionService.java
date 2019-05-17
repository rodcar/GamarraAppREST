package pe.edu.upc.gamarra.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.gamarra.entities.Suscription;
import pe.edu.upc.gamarra.entities.User;

public interface SuscriptionService extends CrudService<Suscription>{
	Optional<Suscription> findById(Long id) throws Exception;
	List<Suscription> findByUserId(User userId) throws Exception;
}
