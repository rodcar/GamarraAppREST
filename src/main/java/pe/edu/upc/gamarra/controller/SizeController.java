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
import pe.edu.upc.gamarra.entities.Size;
import pe.edu.upc.gamarra.service.SizeService;

@RestController
@RequestMapping("/size")
@Api(value = "REST de información sobra las tallas de ropa")
public class SizeController {

	@Autowired
	private SizeService sizeService;
	
	@ApiOperation("Lista de tallas de ropa")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Size>> fetchSizees() {
		try {
			List<Size> sizees = new ArrayList<>();
			sizees = sizeService.findAll();
			return new ResponseEntity<List<Size>>(sizees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Size>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener información de una talla de ropa por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Size> fetchSize(@PathVariable("id") Long id) {
		try {
			Optional<Size> size = sizeService.findById(id);

			if (!size.isPresent()) {
				return new ResponseEntity<Size>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Size>(size.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Size>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de una talla de ropa")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveSize(@Valid @RequestBody Size size) {
		try {
			Size c = new Size();
			c = sizeService.save(size);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de una talla de ropa")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateSize(@Valid @RequestBody Size size) {
		try {
			sizeService.update(size);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar talla de ropa por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSize(@PathVariable("id") Long id) {
		try {
			Optional<Size> size = sizeService.findById(id);

			if (!size.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				sizeService.deleteById(id);
				return new ResponseEntity<>("El size se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
