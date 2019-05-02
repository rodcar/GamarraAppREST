package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Suscription;

@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Long>{
	Optional<Suscription> findById(Long id);
}
