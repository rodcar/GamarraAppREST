package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long>{
	Optional<Tienda> findById(Long id);
}
