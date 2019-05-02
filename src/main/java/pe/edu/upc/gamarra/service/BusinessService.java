package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Business;

public interface BusinessService extends CrudService<Business>{
	Optional<Business> findById(Long id) throws Exception;
}
