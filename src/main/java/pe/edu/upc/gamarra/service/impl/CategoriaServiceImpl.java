package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Categoria;
import pe.edu.upc.gamarra.repository.CategoriaRepository;
import pe.edu.upc.gamarra.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> findAll() throws Exception {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria save(Categoria t) throws Exception {
		return categoriaRepository.save(t);
	}

	@Override
	public Categoria update(Categoria t) throws Exception {
		return categoriaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		categoriaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Categoria> findById(Long id) throws Exception {	
		return categoriaRepository.findById(id);
	}

}
