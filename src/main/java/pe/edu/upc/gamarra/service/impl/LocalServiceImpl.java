package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Local;
import pe.edu.upc.gamarra.repository.LocalRepository;
import pe.edu.upc.gamarra.service.LocalService;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	@Override
	public List<Local> findAll() throws Exception {
		return localRepository.findAll();
	}

	@Override
	public Local save(Local t) throws Exception {
		return localRepository.save(t);
	}

	@Override
	public Local update(Local t) throws Exception {
		return localRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		localRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Local> findById(Long id) throws Exception {	
		return localRepository.findById(id);
	}

}
