package org.lessons.java.spring.api.controllers;

import java.util.List;

import org.lessons.java.spring.models.Pizza;
import org.lessons.java.spring.models.dtos.PizzaDTO;
import org.lessons.java.spring.services.IngredientService;
import org.lessons.java.spring.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaControllerApi {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired IngredientService ingredientService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> index(@RequestParam(required = false) String name) {
		List<Pizza> pizzas = null;
		
		if(name != null)
			pizzas = pizzaService.findByNameContaining(name);
		else
			pizzas = pizzaService.findAll();
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pizza> show(@PathVariable Long id) {
		ResponseEntity<Pizza> result = null;
		Pizza pizza = pizzaService.findById(id);
		
		if(pizza != null)
			result = new ResponseEntity<>(pizza, HttpStatus.OK);
		else
			result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return result;
	}
	
	@PostMapping
	public ResponseEntity<Pizza> store(@Valid @RequestBody PizzaDTO pizza, BindingResult bindingResult) {
		ResponseEntity<Pizza> result = null;
		
		if(bindingResult.hasErrors())
			result = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else {
			Pizza newPizza = new Pizza(pizza.getName(), null, pizza.getPrice(), null);
			pizzaService.save(newPizza);
			
			result = new ResponseEntity<>(newPizza, HttpStatus.CREATED);
		}
		
		return result;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pizza> update(@Valid @RequestBody PizzaDTO pizza, BindingResult bindingResult, @PathVariable Long id) {
		ResponseEntity<Pizza> result = null;
		
		Pizza pizzaToUpdate = pizzaService.findById(id);
		if(pizzaToUpdate == null)
			result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else if(bindingResult.hasErrors())
			result = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		else {
			pizzaToUpdate.setName(pizza.getName());
			pizzaToUpdate.setPrice(pizza.getPrice());
			pizzaService.save(pizzaToUpdate);
			result = new ResponseEntity<>(pizzaToUpdate, HttpStatus.OK);
		}
		
		return result;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		ResponseEntity<Boolean> result = null;
		Pizza pizza = pizzaService.findById(id);
		
		if(pizza == null)
			result = new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		else {
			pizzaService.deleteById(id);
			result = new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return result;
	}
}
