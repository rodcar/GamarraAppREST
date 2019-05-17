package pe.edu.upc.gamarra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Suscription;
import pe.edu.upc.gamarra.entities.User;

@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Long>{
	Optional<Suscription> findById(Long id);
	List<Suscription> findByUserId(User userId);
}
