package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.User;
import pe.edu.upc.gamarra.entities.UserCloth;
import pe.edu.upc.gamarra.repository.UserClothRepository;
import pe.edu.upc.gamarra.service.UserClothService;

@Service
public class UserClothServiceImpl implements UserClothService {

	@Autowired
	private UserClothRepository userClothRepository;
	
	@Override
	public List<UserCloth> findAll() throws Exception {	
		return userClothRepository.findAll();
	}

	@Override
	public UserCloth save(UserCloth t) throws Exception {		
		return userClothRepository.save(t);
	}

	@Override
	public UserCloth update(UserCloth t) throws Exception {		
		return userClothRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		
	}

	@Override
	public void deleteAll() throws Exception {

	}

	@Override
	public Optional<UserCloth> findById(Long id) throws Exception {
		return null;
	}

	@Override
	public Optional<UserCloth> findByUserIdAndClothId(User userId, Cloth clothId) throws Exception {
		return userClothRepository.findByUserIdAndClothId(userId, clothId);
	}

	@Transactional
	@Override
	public void deleteByUserIdAndClothId(User userId, Cloth clothId) throws Exception {
		userClothRepository.deleteByUserIdAndClothId(userId, clothId);
	}

}
