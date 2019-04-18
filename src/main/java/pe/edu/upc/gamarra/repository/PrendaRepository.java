package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Prenda;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Long>{
	Optional<Prenda> findById(Long id);
}
