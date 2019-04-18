package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	Optional<Categoria> findById(Long id);
}
