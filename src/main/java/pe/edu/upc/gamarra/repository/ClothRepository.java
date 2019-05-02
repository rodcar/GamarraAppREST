package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Cloth;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long>{
	Optional<Cloth> findById(Long id);
}
