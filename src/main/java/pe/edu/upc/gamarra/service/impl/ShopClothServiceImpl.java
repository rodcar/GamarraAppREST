package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.entities.ShopCloth;
import pe.edu.upc.gamarra.repository.ShopClothRepository;
import pe.edu.upc.gamarra.service.ShopClothService;

@Service
public class ShopClothServiceImpl implements ShopClothService {

	@Autowired
	private ShopClothRepository shopClothRepository;
	
	@Override
	public List<ShopCloth> findAll() throws Exception {
		return shopClothRepository.findAll();
	}

	@Override
	public ShopCloth save(ShopCloth t) throws Exception {		
		return shopClothRepository.save(t);
	}

	@Override
	public ShopCloth update(ShopCloth t) throws Exception {
		return shopClothRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {}

	@Override
	public void deleteAll() throws Exception {}

	@Override
	public Optional<ShopCloth> findById(Long id) throws Exception {	
		return null;
	}

	@Override
	public Optional<ShopCloth> findByShopIdAndClothId(Shop shopId, Cloth ClothId) throws Exception {
		return shopClothRepository.findByShopIdAndClothId(shopId, ClothId);
	}

	@Transactional
	@Override
	public void deleteByShopIdAndClothId(Shop shopId, Cloth clothId) throws Exception {
		shopClothRepository.deleteByShopIdAndClothId(shopId, clothId);		
	}

	@Override
	public List<ShopCloth> findByShopId(Shop shopId) throws Exception {
		return shopClothRepository.findByShopId(shopId);
	}

	@Override
	public List<ShopCloth> findByClothId(Cloth clothId) throws Exception {
		return shopClothRepository.findByClothId(clothId);
	}

}
