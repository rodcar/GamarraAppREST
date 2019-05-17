package pe.edu.upc.gamarra.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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
import pe.edu.upc.gamarra.entities.Business;
import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.entities.Shop;
import pe.edu.upc.gamarra.entities.Suscription;
import pe.edu.upc.gamarra.entities.User;
import pe.edu.upc.gamarra.entities.UserCloth;
import pe.edu.upc.gamarra.entities.UserClothKey;
import pe.edu.upc.gamarra.service.BusinessService;
import pe.edu.upc.gamarra.service.ClothService;
import pe.edu.upc.gamarra.service.ShopService;
import pe.edu.upc.gamarra.service.SuscriptionService;
import pe.edu.upc.gamarra.service.UserClothService;
import pe.edu.upc.gamarra.service.UserService;

@RestController
@RequestMapping("/users")
@Api(value = "REST de información de usuarios")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClothService clothService;
	
	@Autowired
	private UserClothService userClothService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private SuscriptionService suscriptionService;
	
	@ApiOperation("Lista de usuarios")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> fetchUseres() {
		try {
			List<User> useres = new ArrayList<>();
			useres = userService.findAll();
			return new ResponseEntity<List<User>>(useres, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener información de un usuario por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> fetchUser(@PathVariable("id") Long id) {
		try {
			Optional<User> user = userService.findById(id);

			if (!user.isPresent()) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation("Registro de un usuario")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		try {
			User c = new User();
			c = userService.save(user);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualización de información de un usuario")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUser(@Valid @RequestBody User user) {
		try {
			userService.update(user);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar un usuario por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
		try {
			Optional<User> user = userService.findById(id);

			if (!user.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				userService.deleteById(id);
				return new ResponseEntity<>("El user se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Lista de marcadores")
	@GetMapping(value = "/{id}/markers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cloth>> fetchMarkers(@PathVariable("id") Long userId) {		
		try {
			Optional<User> u = userService.findById(userId);
			List<UserCloth> userMarkers = userClothService.findByUserId(u.get());
			List<Cloth> markers = new ArrayList<>();
		
			Long clothId;
			Optional<Cloth> clothFinded;
			for (UserCloth c : userMarkers) {
				clothId = c.getClothId().getId();
				clothFinded = clothService.findById(clothId);
				markers.add(clothFinded.get());
			}
			return new ResponseEntity<>(markers, HttpStatus.OK);
		} catch (Exception e) {		
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Registro de un marcador")
	@PostMapping(value= "/{id}/markers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUserCloth(@PathVariable("id") Long userId, @RequestBody Cloth cloth) {				
		try {
			Optional<User> user = userService.findById(userId);
			Optional<Cloth> clothFinded = clothService.findById(cloth.getId());
			
			if (user.isPresent() && clothFinded.isPresent()) {			
				User e = user.get();
				Cloth c = clothFinded.get();
				
				UserClothKey key = new UserClothKey();
				key.setClothId(c.getId());
				key.setUserId(e.getId());
				
				UserCloth userCloth = new UserCloth();
				userCloth.setId(key);
				//El valor por defecto de visible es true
				//userCloth.setVisible(true);
				userCloth.setDateAdded(new Date());
				
				userClothService.save(userCloth);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// TODO Se debe implementar la restricción de no cambiar la fecha de registro del marcador
	@ApiOperation("Actualización de la información de un marcador")
	@PutMapping(value = "/{userId}/markers/{clothId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUserCloth(@PathVariable("userId") Long userId, @PathVariable("clothId") Long clothId, @RequestBody UserCloth userCloth) {
		try {
			Optional<User> user = userService.findById(userId);
			Optional<Cloth> clothFinded = clothService.findById(clothId);
			
			User e = user.get();
			Cloth c = clothFinded.get();
			
			UserClothKey key = new UserClothKey();
			key.setClothId(c.getId());
			key.setUserId(e.getId());
			
			userCloth.setId(key);
			userClothService.update(userCloth);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {			
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar un marcador por id")
	@DeleteMapping(value = "/{userId}/markers/{clothId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUserCloth(@PathVariable("userId") Long userId, @PathVariable("clothId") Long clothId) {	
		try {
			Optional<User> u = userService.findById(userId);
			Optional<Cloth> c = clothService.findById(clothId);
			Optional<UserCloth> userCloth = userClothService.findByUserIdAndClothId(u.get(), c.get());
			
			if (!userCloth.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				userClothService.deleteByUserIdAndClothId(u.get(), c.get());
				return new ResponseEntity<String>("El marcador se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("No se pudo encontrar el marcador", HttpStatus.NOT_FOUND);
		}
	}
	
	/* TODO El recurso responde con la información completa del usuario
			no debería responder con la información del usuario */
	@ApiOperation("Lista los negocios de un usuario")
	@GetMapping(value = "/{id}/businesses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Business>> fetchBusinessesByUserId(@PathVariable("id") Long userId) {
		try {
			Optional<User> userFinded = userService.findById(userId);
			
			if(!userFinded.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {			
				List<Business> userBusinesses = businessService.findByUserId(userFinded.get());		
				return new ResponseEntity<>(userBusinesses, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* TODO El recurso responde con la información completa del usuario y del negocio
	no debería responder con la información del usuario y del negocio */	
	@ApiOperation("Lista las tiendas de un negocio")
	@GetMapping(value = "/{userId}/businesses/{businessId}/shops")
	public ResponseEntity<List<Shop>> fetchShopsByBusinessId(@PathVariable("userId") Long userId, @PathVariable("businessId") Long businessId) {
		try {
			Optional<Business> businessFinded = businessService.findById(businessId);
			
			if(!businessFinded.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {				
				List<Shop> businessShops = shopService.findByBusinessId(businessFinded.get());
				return new ResponseEntity<>(businessShops, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation("Lista las suscripciones de un usuario")
	@GetMapping(value = "/{id}/suscriptions", produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<Suscription>> fetchSuscriptionsByUserId(@PathVariable("id") Long id) {
		try {
			Optional<User> userFinded =  userService.findById(id);
			
			if(!userFinded.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				List<Suscription> userSuscriptions = suscriptionService.findByUserId(userFinded.get());
				return new ResponseEntity<List<Suscription>>(userSuscriptions, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
