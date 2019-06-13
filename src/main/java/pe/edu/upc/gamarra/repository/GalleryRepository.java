package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long>{
	Optional<Gallery> findById(Long id);
}
