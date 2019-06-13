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
import io.swagger.annotations.Authorization;
import pe.edu.upc.gamarra.entities.Category;
import pe.edu.upc.gamarra.entities.Cloth;
import pe.edu.upc.gamarra.service.CategoryService;
import pe.edu.upc.gamarra.service.ClothService;

@RestController
@RequestMapping("/categories")
@Api(value = "REST de información sobre las categorias de ropa", tags = {"Categories"})
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ClothService clothService;
	
	@ApiOperation("Lista de categorias de ropa")
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> fetchCategoryes() {
		try {
			List<Category> categoryes = new ArrayList<>();
			categoryes = categoryService.findAll();
			return new ResponseEntity<List<Category>>(categoryes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener categoria de ropa por id")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> fetchCategory(@PathVariable("id") Long id) {
		try {
			Optional<Category> category = categoryService.findById(id);

			if (!category.isPresent()) {
				return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Category>(category.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	// TODO No se debe incluir en la versión de producción del API 	
	@ApiOperation(value = "Registro de una categoria de ropa", authorizations = @Authorization(value = "Bearer"))
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveCategory(@Valid @RequestBody Category category) {
		try {
			Category c = new Category();
			c = categoryService.save(category);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// TODO No se debe incluir en la versión de producción del API 
	@ApiOperation(value = "Actualización de información de una categoria de ropa", authorizations = @Authorization(value = "Bearer"))
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCategory(@Valid @RequestBody Category category) {
		try {
			categoryService.update(category);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// TODO No se debe incluir en la versión de producción del API 
	@ApiOperation(value = "Eliminar una categoria de ropa por id", authorizations = @Authorization(value = "Bearer"))
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
		try {
			Optional<Category> category = categoryService.findById(id);

			if (!category.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} else {
				categoryService.deleteById(id);
				return new ResponseEntity<>("El category se elimino", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Lista de prendas por categoría")
	@GetMapping(value = "/{id}/clothes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cloth>> getClothesByCategoryId(@PathVariable("id") Long id) {
		try {
			Optional<Category> category = categoryService.findById(id);
			List<Cloth> clothes = clothService.findByCategoryId(category.get());
			return new ResponseEntity<>(clothes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Cloth>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
