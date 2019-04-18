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
import pe.edu.upc.gamarra.entities.Prenda;
import pe.edu.upc.gamarra.service.PrendaService;

@RestController
@RequestMapping("/prenda")
@Api(value = "REST de información de prenda")
public class PrendaController {

	@Autowired
	private PrendaService prendaService;
	
	@ApiOperation("Lista de prenda")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Prenda>> fetchPrendaes() {
		try {
			List<Prenda> prendaes = new ArrayList<>();
			prendaes = prendaService.findAll();
			return new ResponseEntity<List<Prenda>>(prendaes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Prenda>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener prenda por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Prenda> fetchPrenda(@PathVariable("id") Long id) {
		try {
			Optional<Prenda> prenda = prendaService.findById(id);

			if (!prenda.isPresent()) {
				return new ResponseEntity<Prenda>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Prenda>(prenda.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Prenda>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro prenda")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> savePrenda(@Valid @RequestBody Prenda prenda) {
		try {
			Prenda c = new Prenda();
			c = prendaService.save(prenda);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un prenda")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePrenda(@Valid @RequestBody Prenda prenda) {
		try {
			prendaService.update(prenda);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar prenda por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePrenda(@PathVariable("id") Long id) {
		try {
			Optional<Prenda> prenda = prendaService.findById(id);

			if (!prenda.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				prendaService.deleteById(id);
				return new ResponseEntity<>("El prenda se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
