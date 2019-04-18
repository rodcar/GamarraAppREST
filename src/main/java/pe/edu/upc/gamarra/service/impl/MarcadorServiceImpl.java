package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Marcador;
import pe.edu.upc.gamarra.repository.MarcadorRepository;
import pe.edu.upc.gamarra.service.MarcadorService;

@Service
public class MarcadorServiceImpl implements MarcadorService {

	@Autowired
	private MarcadorRepository marcadorRepository;
	
	@Override
	public List<Marcador> findAll() throws Exception {
		return marcadorRepository.findAll();
	}

	@Override
	public Marcador save(Marcador t) throws Exception {
		return marcadorRepository.save(t);
	}

	@Override
	public Marcador update(Marcador t) throws Exception {
		return marcadorRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		marcadorRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Marcador> findById(Long id) throws Exception {	
		return marcadorRepository.findById(id);
	}

}
