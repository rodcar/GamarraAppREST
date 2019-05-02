package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>{
	Optional<Size> findById(Long id);
}
