package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Talla;
import pe.edu.upc.gamarra.repository.TallaRepository;
import pe.edu.upc.gamarra.service.TallaService;

@Service
public class TallaServiceImpl implements TallaService {

	@Autowired
	private TallaRepository tallaRepository;
	
	@Override
	public List<Talla> findAll() throws Exception {
		return tallaRepository.findAll();
	}

	@Override
	public Talla save(Talla t) throws Exception {
		return tallaRepository.save(t);
	}

	@Override
	public Talla update(Talla t) throws Exception {
		return tallaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		tallaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Talla> findById(Long id) throws Exception {	
		return tallaRepository.findById(id);
	}

}
