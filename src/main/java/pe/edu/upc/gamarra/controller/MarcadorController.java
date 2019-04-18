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
import pe.edu.upc.gamarra.entities.Marcador;
import pe.edu.upc.gamarra.service.MarcadorService;

@RestController
@RequestMapping("/marcador")
@Api(value = "REST de información de marcador")
public class MarcadorController {

	@Autowired
	private MarcadorService marcadorService;
	
	@ApiOperation("Lista de marcador")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Marcador>> fetchMarcadores() {
		try {
			List<Marcador> marcadores = new ArrayList<>();
			marcadores = marcadorService.findAll();
			return new ResponseEntity<List<Marcador>>(marcadores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Marcador>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener marcador por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Marcador> fetchMarcador(@PathVariable("id") Long id) {
		try {
			Optional<Marcador> marcador = marcadorService.findById(id);

			if (!marcador.isPresent()) {
				return new ResponseEntity<Marcador>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Marcador>(marcador.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Marcador>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro marcador")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveMarcador(@Valid @RequestBody Marcador marcador) {
		try {
			Marcador c = new Marcador();
			c = marcadorService.save(marcador);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un marcador")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateMarcador(@Valid @RequestBody Marcador marcador) {
		try {
			marcadorService.update(marcador);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar marcador por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteMarcador(@PathVariable("id") Long id) {
		try {
			Optional<Marcador> marcador = marcadorService.findById(id);

			if (!marcador.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				marcadorService.deleteById(id);
				return new ResponseEntity<>("El marcador se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
