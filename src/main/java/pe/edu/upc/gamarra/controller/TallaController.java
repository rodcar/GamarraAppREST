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
import pe.edu.upc.gamarra.entities.Talla;
import pe.edu.upc.gamarra.service.TallaService;

@RestController
@RequestMapping("/talla")
@Api(value = "REST de información de talla")
public class TallaController {

	@Autowired
	private TallaService tallaService;
	
	@ApiOperation("Lista de talla")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Talla>> fetchTallaes() {
		try {
			List<Talla> tallaes = new ArrayList<>();
			tallaes = tallaService.findAll();
			return new ResponseEntity<List<Talla>>(tallaes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Talla>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener talla por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Talla> fetchTalla(@PathVariable("id") Long id) {
		try {
			Optional<Talla> talla = tallaService.findById(id);

			if (!talla.isPresent()) {
				return new ResponseEntity<Talla>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Talla>(talla.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Talla>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro talla")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveTalla(@Valid @RequestBody Talla talla) {
		try {
			Talla c = new Talla();
			c = tallaService.save(talla);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un talla")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateTalla(@Valid @RequestBody Talla talla) {
		try {
			tallaService.update(talla);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar talla por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTalla(@PathVariable("id") Long id) {
		try {
			Optional<Talla> talla = tallaService.findById(id);

			if (!talla.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				tallaService.deleteById(id);
				return new ResponseEntity<>("El talla se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
