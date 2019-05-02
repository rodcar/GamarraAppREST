package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Category;
import pe.edu.upc.gamarra.repository.CategoryRepository;
import pe.edu.upc.gamarra.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() throws Exception {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category t) throws Exception {
		return categoryRepository.save(t);
	}

	@Override
	public Category update(Category t) throws Exception {
		return categoryRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		categoryRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Category> findById(Long id) throws Exception {	
		return categoryRepository.findById(id);
	}

}
