package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.User;

public interface UserService extends CrudService<User>{
	Optional<User> findById(Long id) throws Exception;
}
