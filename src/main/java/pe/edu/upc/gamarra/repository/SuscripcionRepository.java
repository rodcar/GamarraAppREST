package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Suscripcion;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long>{
	Optional<Suscripcion> findById(Long id);
}
