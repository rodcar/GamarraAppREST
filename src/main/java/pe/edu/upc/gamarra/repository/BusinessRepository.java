package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{
	Optional<Business> findById(Long id);
}