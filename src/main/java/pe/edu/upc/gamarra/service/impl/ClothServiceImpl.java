package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Category;
import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.repository.ClothRepository;
import pe.edu.upc.gamarra.service.ClothService;

@Service
public class ClothServiceImpl implements ClothService {

	@Autowired
	private ClothRepository clothRepository;
	
	@Override
	public List<Cloth> findAll() throws Exception {
		return clothRepository.findAll();
	}

	@Override
	public Cloth save(Cloth t) throws Exception {
		return clothRepository.save(t);
	}

	@Override
	public Cloth update(Cloth t) throws Exception {
		return clothRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		clothRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Cloth> findById(Long id) throws Exception {	
		return clothRepository.findById(id);
	}

	@Override
	public List<Cloth> findByCategoryId(Category category) {
		return clothRepository.findByCategoryId(category);
	}

}
