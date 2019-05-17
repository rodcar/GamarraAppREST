package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Business;
import pe.edu.upc.gamarra.entities.User;
import pe.edu.upc.gamarra.repository.BusinessRepository;
import pe.edu.upc.gamarra.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessRepository businessRepository;
	
	@Override
	public List<Business> findAll() throws Exception {
		return businessRepository.findAll();
	}

	@Override
	public Business save(Business t) throws Exception {
		return businessRepository.save(t);
	}

	@Override
	public Business update(Business t) throws Exception {
		return businessRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		businessRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Business> findById(Long id) throws Exception {	
		return businessRepository.findById(id);
	}

	@Override
	public List<Business> findByUserId(User userId) throws Exception {
		return businessRepository.findByUserId(userId);
	}

}
