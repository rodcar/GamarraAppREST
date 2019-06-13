package pe.edu.upc.gamarra.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.gamarra.entities.Business;
import pe.edu.upc.gamarra.entities.User;

public interface BusinessService extends CrudService<Business>{
	Optional<Business> findById(Long id) throws Exception;
	List<Business> findByUserId(User userId) throws Exception;
}
