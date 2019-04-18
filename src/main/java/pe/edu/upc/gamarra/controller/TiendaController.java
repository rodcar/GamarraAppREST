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
import pe.edu.upc.gamarra.entities.Tienda;
import pe.edu.upc.gamarra.service.TiendaService;

@RestController
@RequestMapping("/tienda")
@Api(value = "REST de información de tienda")
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;
	
	@ApiOperation("Lista de tienda")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tienda>> fetchTiendaes() {
		try {
			List<Tienda> tiendaes = new ArrayList<>();
			tiendaes = tiendaService.findAll();
			return new ResponseEntity<List<Tienda>>(tiendaes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Tienda>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener tienda por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tienda> fetchTienda(@PathVariable("id") Long id) {
		try {
			Optional<Tienda> tienda = tiendaService.findById(id);

			if (!tienda.isPresent()) {
				return new ResponseEntity<Tienda>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Tienda>(tienda.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Tienda>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro tienda")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveTienda(@Valid @RequestBody Tienda tienda) {
		try {
			Tienda c = new Tienda();
			c = tiendaService.save(tienda);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un tienda")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateTienda(@Valid @RequestBody Tienda tienda) {
		try {
			tiendaService.update(tienda);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar tienda por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTienda(@PathVariable("id") Long id) {
		try {
			Optional<Tienda> tienda = tiendaService.findById(id);

			if (!tienda.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				tiendaService.deleteById(id);
				return new ResponseEntity<>("El tienda se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
