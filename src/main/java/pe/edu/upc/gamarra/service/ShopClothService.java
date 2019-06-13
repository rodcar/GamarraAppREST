package pe.edu.upc.gamarra.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.entities.ShopCloth;

public interface ShopClothService extends CrudService<ShopCloth>{
	Optional<ShopCloth> findByShopIdAndClothId(Shop shopId, Cloth ClothId) throws Exception;
	
	void deleteByShopIdAndClothId(Shop shopId, Cloth clothId) throws Exception;
	
	List<ShopCloth> findByShopId(Shop shopId) throws Exception;
	
	List<ShopCloth> findByClothId(Cloth clothId) throws Exception;
}
