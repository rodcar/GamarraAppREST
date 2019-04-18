package pe.edu.upc.gamarra.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.edu.upc.gamarra.entities.Usuario;
import pe.edu.upc.gamarra.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@Api(value = "REST de información de usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation("Lista de usuario")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> fetchUsuarioes() {
		try {
			List<Usuario> usuarioes = new ArrayList<>();
			usuarioes = usuarioService.findAll();
			return new ResponseEntity<List<Usuario>>(usuarioes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener usuario por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> fetchUsuario(@PathVariable("id") Long id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);

			if (!usuario.isPresent()) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro usuario")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUsuario(@Valid @RequestBody Usuario usuario) {
		try {
			Usuario c = new Usuario();
			c = usuarioService.save(usuario);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un usuario")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUsuario(@Valid @RequestBody Usuario usuario) {
		try {
			usuarioService.update(usuario);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar usuario por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUsuario(@PathVariable("id") Long id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);

			if (!usuario.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				usuarioService.deleteById(id);
				return new ResponseEntity<>("El usuario se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
