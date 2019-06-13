package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Gallery;
import pe.edu.upc.gamarra.repository.GalleryRepository;
import pe.edu.upc.gamarra.service.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	private GalleryRepository galleryRepository;
	
	@Override
	public List<Gallery> findAll() throws Exception {
		return galleryRepository.findAll();
	}

	@Override
	public Gallery save(Gallery t) throws Exception {
		return galleryRepository.save(t);
	}

	@Override
	public Gallery update(Gallery t) throws Exception {
		return galleryRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		galleryRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Gallery> findById(Long id) throws Exception {	
		return galleryRepository.findById(id);
	}

}
