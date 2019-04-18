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
import pe.edu.upc.gamarra.entities.Suscripcion;
import pe.edu.upc.gamarra.service.SuscripcionService;

@RestController
@RequestMapping("/suscripcion")
@Api(value = "REST de información de suscripcion")
public class SuscripcionController {

	@Autowired
	private SuscripcionService suscripcionService;
	
	@ApiOperation("Lista de suscripcion")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Suscripcion>> fetchSuscripciones() {
		try {
			List<Suscripcion> suscripciones = new ArrayList<>();
			suscripciones = suscripcionService.findAll();
			return new ResponseEntity<List<Suscripcion>>(suscripciones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Suscripcion>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener suscripcion por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Suscripcion> fetchSuscripcion(@PathVariable("id") Long id) {
		try {
			Optional<Suscripcion> suscripcion = suscripcionService.findById(id);

			if (!suscripcion.isPresent()) {
				return new ResponseEntity<Suscripcion>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Suscripcion>(suscripcion.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Suscripcion>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro suscripcion")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSuscripcion(@Valid @RequestBody Suscripcion suscripcion) {
		try {
			Suscripcion c = new Suscripcion();
			c = suscripcionService.save(suscripcion);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un suscripcion")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateSuscripcion(@Valid @RequestBody Suscripcion suscripcion) {
		try {
			suscripcionService.update(suscripcion);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar suscripcion por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSuscripcion(@PathVariable("id") Long id) {
		try {
			Optional<Suscripcion> suscripcion = suscripcionService.findById(id);

			if (!suscripcion.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				suscripcionService.deleteById(id);
				return new ResponseEntity<>("El suscripcion se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
