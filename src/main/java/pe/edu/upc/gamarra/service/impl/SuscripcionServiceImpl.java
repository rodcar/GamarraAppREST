package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Suscripcion;
import pe.edu.upc.gamarra.repository.SuscripcionRepository;
import pe.edu.upc.gamarra.service.SuscripcionService;

@Service
public class SuscripcionServiceImpl implements SuscripcionService {

	@Autowired
	private SuscripcionRepository suscripcionRepository;
	
	@Override
	public List<Suscripcion> findAll() throws Exception {
		return suscripcionRepository.findAll();
	}

	@Override
	public Suscripcion save(Suscripcion t) throws Exception {
		return suscripcionRepository.save(t);
	}

	@Override
	public Suscripcion update(Suscripcion t) throws Exception {
		return suscripcionRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		suscripcionRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Suscripcion> findById(Long id) throws Exception {	
		return suscripcionRepository.findById(id);
	}

}
