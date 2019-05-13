package pe.edu.upc.gamarra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.entities.ShopCloth;
import pe.edu.upc.gamarra.entities.ShopClothKey;

@Repository
public interface ShopClothRepository extends JpaRepository<ShopCloth, ShopClothKey>{
	Optional<ShopCloth> findByShopIdAndClothId(Shop shopId, Cloth ClothId) throws Exception;
	
	void deleteByShopIdAndClothId(Shop shopId, Cloth clothId) throws Exception;
	
	List<ShopCloth> findByShopId(Shop shopId) throws Exception;
}
