package pe.edu.upc.gamarra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Business;
import pe.edu.upc.gamarra.entities.User;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{
	Optional<Business> findById(Long id);
	List<Business> findByUserId(User userId);
}
