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
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.service.ShopService;

@RestController
@RequestMapping("/shop")
@Api(value = "REST de información de tiendas")
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@ApiOperation("Lista de tiendas")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Shop>> fetchShopes() {
		try {
			List<Shop> shopes = new ArrayList<>();
			shopes = shopService.findAll();
			return new ResponseEntity<List<Shop>>(shopes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Shop>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener información de una tienda por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shop> fetchShop(@PathVariable("id") Long id) {
		try {
			Optional<Shop> shop = shopService.findById(id);

			if (!shop.isPresent()) {
				return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Shop>(shop.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Shop>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de una tienda")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveShop(@Valid @RequestBody Shop shop) {
		try {
			Shop c = new Shop();
			c = shopService.save(shop);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de una tienda")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateShop(@Valid @RequestBody Shop shop) {
		try {
			shopService.update(shop);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar una tienda por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteShop(@PathVariable("id") Long id) {
		try {
			Optional<Shop> shop = shopService.findById(id);

			if (!shop.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				shopService.deleteById(id);
				return new ResponseEntity<>("El shop se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
