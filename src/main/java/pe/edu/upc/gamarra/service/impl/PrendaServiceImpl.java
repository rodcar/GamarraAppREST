package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Prenda;
import pe.edu.upc.gamarra.repository.PrendaRepository;
import pe.edu.upc.gamarra.service.PrendaService;

@Service
public class PrendaServiceImpl implements PrendaService {

	@Autowired
	private PrendaRepository prendaRepository;
	
	@Override
	public List<Prenda> findAll() throws Exception {
		return prendaRepository.findAll();
	}

	@Override
	public Prenda save(Prenda t) throws Exception {
		return prendaRepository.save(t);
	}

	@Override
	public Prenda update(Prenda t) throws Exception {
		return prendaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		prendaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Prenda> findById(Long id) throws Exception {	
		return prendaRepository.findById(id);
	}

}
