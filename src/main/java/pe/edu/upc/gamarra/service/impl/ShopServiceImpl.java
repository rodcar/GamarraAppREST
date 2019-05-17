package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Business;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.repository.ShopRepository;
import pe.edu.upc.gamarra.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	@Override
	public List<Shop> findAll() throws Exception {
		return shopRepository.findAll();
	}

	@Override
	public Shop save(Shop t) throws Exception {
		return shopRepository.save(t);
	}

	@Override
	public Shop update(Shop t) throws Exception {
		return shopRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		shopRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Shop> findById(Long id) throws Exception {	
		return shopRepository.findById(id);
	}

	@Override
	public List<Shop> findByBusinessId(Business businessId) throws Exception {
		return shopRepository.findByBusinessId(businessId);
	}

}
