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
import pe.edu.upc.gamarra.entities.Suscription;
import pe.edu.upc.gamarra.service.SuscriptionService;

@RestController
@RequestMapping("/suscriptions")
@Api(value = "REST de información de suscripciones")
public class SuscriptionController {

	@Autowired
	private SuscriptionService suscriptionService;
	
	@ApiOperation("Lista de suscripciones")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Suscription>> fetchSuscriptiones() {
		try {
			List<Suscription> suscriptiones = new ArrayList<>();
			suscriptiones = suscriptionService.findAll();
			return new ResponseEntity<List<Suscription>>(suscriptiones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Suscription>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener información de una suscripción por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Suscription> fetchSuscription(@PathVariable("id") Long id) {
		try {
			Optional<Suscription> suscription = suscriptionService.findById(id);

			if (!suscription.isPresent()) {
				return new ResponseEntity<Suscription>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Suscription>(suscription.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Suscription>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de una suscripción")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSuscription(@Valid @RequestBody Suscription suscription) {
		try {
			Suscription c = new Suscription();
			c = suscriptionService.save(suscription);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de una suscripción")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateSuscription(@Valid @RequestBody Suscription suscription) {
		try {
			suscriptionService.update(suscription);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar una suscripción por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSuscription(@PathVariable("id") Long id) {
		try {
			Optional<Suscription> suscription = suscriptionService.findById(id);

			if (!suscription.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				suscriptionService.deleteById(id);
				return new ResponseEntity<>("El suscription se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
