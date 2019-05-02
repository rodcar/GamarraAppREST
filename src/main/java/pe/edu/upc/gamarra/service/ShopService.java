package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Shop;

public interface ShopService extends CrudService<Shop>{
	Optional<Shop> findById(Long id) throws Exception;
}
