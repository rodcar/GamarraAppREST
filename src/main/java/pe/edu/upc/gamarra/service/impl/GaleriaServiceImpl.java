package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Galeria;
import pe.edu.upc.gamarra.repository.GaleriaRepository;
import pe.edu.upc.gamarra.service.GaleriaService;

@Service
public class GaleriaServiceImpl implements GaleriaService {

	@Autowired
	private GaleriaRepository galeriaRepository;
	
	@Override
	public List<Galeria> findAll() throws Exception {
		return galeriaRepository.findAll();
	}

	@Override
	public Galeria save(Galeria t) throws Exception {
		return galeriaRepository.save(t);
	}

	@Override
	public Galeria update(Galeria t) throws Exception {
		return galeriaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		galeriaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Galeria> findById(Long id) throws Exception {	
		return galeriaRepository.findById(id);
	}

}
