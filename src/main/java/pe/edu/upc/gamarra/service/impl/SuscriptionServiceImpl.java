package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Suscription;
import pe.edu.upc.gamarra.repository.SuscriptionRepository;
import pe.edu.upc.gamarra.service.SuscriptionService;

@Service
public class SuscriptionServiceImpl implements SuscriptionService {

	@Autowired
	private SuscriptionRepository suscriptionRepository;
	
	@Override
	public List<Suscription> findAll() throws Exception {
		return suscriptionRepository.findAll();
	}

	@Override
	public Suscription save(Suscription t) throws Exception {
		return suscriptionRepository.save(t);
	}

	@Override
	public Suscription update(Suscription t) throws Exception {
		return suscriptionRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		suscriptionRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Suscription> findById(Long id) throws Exception {	
		return suscriptionRepository.findById(id);
	}

}
