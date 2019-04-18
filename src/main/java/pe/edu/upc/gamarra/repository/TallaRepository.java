package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Talla;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Long>{
	Optional<Talla> findById(Long id);
}
