package pe.edu.upc.gamarra.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import pe.edu.upc.gamarra.entities.Business;
import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.entities.ShopCloth;
import pe.edu.upc.gamarra.entities.ShopClothKey;
import pe.edu.upc.gamarra.service.BusinessService;
import pe.edu.upc.gamarra.service.ClothService;
import pe.edu.upc.gamarra.service.ShopClothService;
import pe.edu.upc.gamarra.service.ShopService;

@RestController
@RequestMapping("/businesses")
@Api(value = "REST de información de los negocios")
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private ShopClothService shopClothService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ClothService clothService;
	
	@ApiOperation("Lista de negocios")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Business>> fetchBusinesses() {
		try {
			List<Business> businesses = new ArrayList<>();
			businesses = businessService.findAll();
			return new ResponseEntity<List<Business>>(businesses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Business>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener información de una negocio por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Business> fetchBusiness(@PathVariable("id") Long id) {
		try {
			Optional<Business> business = businessService.findById(id);

			if (!business.isPresent()) {
				return new ResponseEntity<Business>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Business>(business.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Business>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de un negocio")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveBusiness(@Valid @RequestBody Business business) {
		try {
			Business c = new Business();
			c = businessService.save(business);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un negocio")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBusiness(@Valid @RequestBody Business business) {
		try {
			businessService.update(business);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar un negocio por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteBusiness(@PathVariable("id") Long id) {
		try {
			Optional<Business> business = businessService.findById(id);

			if (!business.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				businessService.deleteById(id);
				return new ResponseEntity<>("El business se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Registro de una prenda en una tienda")
	@PostMapping(value = "/{businessId}/shops/{shopId}/clothes")
	public ResponseEntity<Object> saveShopCloth(@PathVariable("businessId") Long businessId, @PathVariable("shopId") Long shopId, @RequestBody Cloth cloth) {
		try {
			ShopClothKey key = new ShopClothKey();
			key.setShopId(shopId);
			key.setClothId(cloth.getId());
			ShopCloth shopCloth = new ShopCloth();
			shopCloth.setId(key);
			shopCloth.setDateAdded(new Date());
			//TODO Evaluar si se requiere el atributo editable
			shopCloth.setEditable(true);
			shopCloth = shopClothService.save(shopCloth);
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("businessId", businessId.toString());
			uriVariables.put("shopId", shopId.toString());
			uriVariables.put("clothId", cloth.getId().toString());
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{clothId}").buildAndExpand(uriVariables)
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
