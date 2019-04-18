package pe.edu.upc.gamarra.service;

import java.util.Optional;

import pe.edu.upc.gamarra.entities.Usuario;

public interface UsuarioService extends CrudService<Usuario>{
	Optional<Usuario> findById(Long id) throws Exception;
}
