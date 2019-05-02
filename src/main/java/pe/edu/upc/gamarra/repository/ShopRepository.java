package pe.edu.upc.gamarra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{
	Optional<Shop> findById(Long id);
}
