package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Marcador;

@Repository
public interface MarcadorRepository extends JpaRepository<Marcador, Long>{
	Optional<Marcador> findById(Long id);
}
