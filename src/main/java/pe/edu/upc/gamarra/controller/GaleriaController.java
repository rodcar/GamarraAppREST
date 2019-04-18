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
import pe.edu.upc.gamarra.entities.Galeria;
import pe.edu.upc.gamarra.service.GaleriaService;

@RestController
@RequestMapping("/galeria")
@Api(value = "REST de información de galeria")
public class GaleriaController {

	@Autowired
	private GaleriaService galeriaService;
	
	@ApiOperation("Lista de galeria")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Galeria>> fetchGaleriaes() {
		try {
			List<Galeria> galeriaes = new ArrayList<>();
			galeriaes = galeriaService.findAll();
			return new ResponseEntity<List<Galeria>>(galeriaes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Galeria>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener galeria por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Galeria> fetchGaleria(@PathVariable("id") Long id) {
		try {
			Optional<Galeria> galeria = galeriaService.findById(id);

			if (!galeria.isPresent()) {
				return new ResponseEntity<Galeria>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Galeria>(galeria.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Galeria>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro galeria")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveGaleria(@Valid @RequestBody Galeria galeria) {
		try {
			Galeria c = new Galeria();
			c = galeriaService.save(galeria);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un galeria")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateGaleria(@Valid @RequestBody Galeria galeria) {
		try {
			galeriaService.update(galeria);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar galeria por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteGaleria(@PathVariable("id") Long id) {
		try {
			Optional<Galeria> galeria = galeriaService.findById(id);

			if (!galeria.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				galeriaService.deleteById(id);
				return new ResponseEntity<>("El galeria se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
