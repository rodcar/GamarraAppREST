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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.service.ClothService;

@RestController
@RequestMapping("/clothes")
@Api(value = "REST de información sobre prendas")
public class ClothController {

	@Autowired
	private ClothService clothService;
	
	@ApiOperation("Lista de prendas")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cloth>> fetchClothes(@RequestParam(name = "name", required = false) String name) {
		if (name == null) {
			try {
				List<Cloth> clothes = new ArrayList<>();
				clothes = clothService.findAll();
				return new ResponseEntity<List<Cloth>>(clothes, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<Cloth>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			List<Cloth> clothes = clothService.findByNameContaining(name);
			return new ResponseEntity<List<Cloth>>(clothes, HttpStatus.OK);
		}
	}
	
	@ApiOperation("Obtener información de una prenda por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cloth> fetchCloth(@PathVariable("id") Long id) {
		try {
			Optional<Cloth> cloth = clothService.findById(id);

			if (!cloth.isPresent()) {
				return new ResponseEntity<Cloth>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Cloth>(cloth.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Cloth>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de una prenda")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveCloth(@Valid @RequestBody Cloth cloth) {
		try {
			Cloth c = new Cloth();
			c = clothService.save(cloth);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de una prenda")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCloth(@Valid @RequestBody Cloth cloth) {
		try {
			clothService.update(cloth);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar una prenda por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCloth(@PathVariable("id") Long id) {
		try {
			Optional<Cloth> cloth = clothService.findById(id);

			if (!cloth.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				clothService.deleteById(id);
				return new ResponseEntity<>("El cloth se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
