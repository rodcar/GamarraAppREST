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
import org.springframework.security.access.prepost.PreAuthorize;
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
import io.swagger.annotations.Authorization;
import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.entities.ShopCloth;
import pe.edu.upc.gamarra.service.ClothService;
import pe.edu.upc.gamarra.service.ShopClothService;
import pe.edu.upc.gamarra.service.ShopService;

@RestController
@RequestMapping("/clothes")
@Api(value = "REST de información sobre prendas", tags = {"Clothes"})
public class ClothController {

	@Autowired
	private ClothService clothService;
	
	@Autowired
	private ShopClothService shopClothService;
	
	@Autowired
	private ShopService shopService;
	
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
	
	@ApiOperation(value = "Registro de una prenda", authorizations = @Authorization(value = "Bearer"))
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
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
	
	@ApiOperation(value = "Actualización de información de una prenda", authorizations = @Authorization(value = "Bearer"))
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> updateCloth(@Valid @RequestBody Cloth cloth) {
		try {
			clothService.update(cloth);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Eliminar una prenda por id", authorizations = @Authorization(value = "Bearer"))
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
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
	
	@ApiOperation("Lista de tiendas en las que se encuentra una prenda")
	@GetMapping(value = "/{id}/shops", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Shop>> findShopsByClothId(@PathVariable("id") Long id) {
		try {
			Optional<Cloth> clothFinded = clothService.findById(id);
			List<ShopCloth> shopCloth = shopClothService.findByClothId(clothFinded.get());
			
			Optional<Shop> shop;
			List<Shop> shops = new ArrayList<>();
			for(ShopCloth sc : shopCloth) {
				shop = shopService.findById(sc.getShopId().getId());
				shops.add(shop.get());
			}			
			return new ResponseEntity<>(shops, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
