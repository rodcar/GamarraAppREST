package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Size;
import pe.edu.upc.gamarra.repository.SizeRepository;
import pe.edu.upc.gamarra.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;
	
	@Override
	public List<Size> findAll() throws Exception {
		return sizeRepository.findAll();
	}

	@Override
	public Size save(Size t) throws Exception {
		return sizeRepository.save(t);
	}

	@Override
	public Size update(Size t) throws Exception {
		return sizeRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		sizeRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Size> findById(Long id) throws Exception {	
		return sizeRepository.findById(id);
	}

}
