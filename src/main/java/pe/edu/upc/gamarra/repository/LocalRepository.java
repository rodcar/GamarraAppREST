package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long>{
	Optional<Local> findById(Long id);
}
