package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Tienda;
import pe.edu.upc.gamarra.repository.TiendaRepository;
import pe.edu.upc.gamarra.service.TiendaService;

@Service
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	private TiendaRepository tiendaRepository;
	
	@Override
	public List<Tienda> findAll() throws Exception {
		return tiendaRepository.findAll();
	}

	@Override
	public Tienda save(Tienda t) throws Exception {
		return tiendaRepository.save(t);
	}

	@Override
	public Tienda update(Tienda t) throws Exception {
		return tiendaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		tiendaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Tienda> findById(Long id) throws Exception {	
		return tiendaRepository.findById(id);
	}

}
