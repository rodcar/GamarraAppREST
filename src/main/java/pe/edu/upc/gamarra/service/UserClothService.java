package pe.edu.upc.gamarra.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.User;
import pe.edu.upc.gamarra.entities.UserCloth;

public interface UserClothService extends CrudService<UserCloth>{
	Optional<UserCloth> findByUserIdAndClothId(User userId, Cloth clothId) throws Exception;
	
	void deleteByUserIdAndClothId(User userId, Cloth clothId) throws Exception;
	
	List<UserCloth> findByUserId(User userId) throws Exception;
}
