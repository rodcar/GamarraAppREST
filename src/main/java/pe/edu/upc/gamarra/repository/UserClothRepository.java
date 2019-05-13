package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.User;
import pe.edu.upc.gamarra.entities.UserCloth;
import pe.edu.upc.gamarra.entities.UserClothKey;

@Repository
public interface UserClothRepository extends JpaRepository<UserCloth, UserClothKey>{
	Optional<UserCloth> findByUserIdAndClothId(User userId, Cloth clothId);
	
	void deleteByUserIdAndClothId(User userId, Cloth clothId) throws Exception;
}
