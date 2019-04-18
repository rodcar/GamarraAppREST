package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Galeria;

@Repository
public interface GaleriaRepository extends JpaRepository<Galeria, Long>{
	Optional<Galeria> findById(Long id);
}
