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
import pe.edu.upc.gamarra.entities.Categoria;
import pe.edu.upc.gamarra.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
@Api(value = "REST de información de categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation("Lista de categoria")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria>> fetchCategoriaes() {
		try {
			List<Categoria> categoriaes = new ArrayList<>();
			categoriaes = categoriaService.findAll();
			return new ResponseEntity<List<Categoria>>(categoriaes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Categoria>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener categoria por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> fetchCategoria(@PathVariable("id") Long id) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);

			if (!categoria.isPresent()) {
				return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Categoria>(categoria.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro categoria")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveCategoria(@Valid @RequestBody Categoria categoria) {
		try {
			Categoria c = new Categoria();
			c = categoriaService.save(categoria);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un categoria")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCategoria(@Valid @RequestBody Categoria categoria) {
		try {
			categoriaService.update(categoria);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar categoria por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCategoria(@PathVariable("id") Long id) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);

			if (!categoria.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				categoriaService.deleteById(id);
				return new ResponseEntity<>("El categoria se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
