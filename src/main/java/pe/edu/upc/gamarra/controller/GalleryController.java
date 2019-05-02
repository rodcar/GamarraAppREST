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
import pe.edu.upc.gamarra.entities.Gallery;
import pe.edu.upc.gamarra.service.GalleryService;

@RestController
@RequestMapping("/gallery")
@Api(value = "REST de información de galerías")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@ApiOperation("Lista de galerías")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Gallery>> fetchGalleryes() {
		try {
			List<Gallery> galleryes = new ArrayList<>();
			galleryes = galleryService.findAll();
			return new ResponseEntity<List<Gallery>>(galleryes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Gallery>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener información de una galería por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Gallery> fetchGallery(@PathVariable("id") Long id) {
		try {
			Optional<Gallery> gallery = galleryService.findById(id);

			if (!gallery.isPresent()) {
				return new ResponseEntity<Gallery>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Gallery>(gallery.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Gallery>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de una galería")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveGallery(@Valid @RequestBody Gallery gallery) {
		try {
			Gallery c = new Gallery();
			c = galleryService.save(gallery);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un galería")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateGallery(@Valid @RequestBody Gallery gallery) {
		try {
			galleryService.update(gallery);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar una galería por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteGallery(@PathVariable("id") Long id) {
		try {
			Optional<Gallery> gallery = galleryService.findById(id);

			if (!gallery.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				galleryService.deleteById(id);
				return new ResponseEntity<>("El gallery se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
