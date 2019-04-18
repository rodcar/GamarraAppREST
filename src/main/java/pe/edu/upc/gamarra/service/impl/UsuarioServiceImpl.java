package pe.edu.upc.gamarra.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.gamarra.entities.Usuario;
import pe.edu.upc.gamarra.repository.UsuarioRepository;
import pe.edu.upc.gamarra.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	public Usuario update(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		usuarioRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		
	}

	@Override
	public Optional<Usuario> findById(Long id) throws Exception {	
		return usuarioRepository.findById(id);
	}

}
