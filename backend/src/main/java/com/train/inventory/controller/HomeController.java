package com.train.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.inventory.entity.Product;
import com.train.inventory.repository.ProductRepo;

//for front end
@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
public class HomeController {
	@Autowired
	ProductRepo productRepo;
	ObjectMapper mp = new ObjectMapper();
	// READ product
	@GetMapping("/products")
	public List<Product>getAllTrain() {
		try {
			List<Product> result = productRepo.findAll();
			System.err.println(mp.writeValueAsString(result));
			return result;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@GetMapping("/getTrain/{id}")
	public Product getTrainByID(@PathVariable("id") String id) {
		Optional<Product> result = productRepo.findById(id);
		if (result == null) {
			return null;
		}
		return result.get();
	}

	@GetMapping("/getTrainWithName")
	public List<Product> getTrainByName(@RequestParam(required = true) String name) {
		List<Product> result = productRepo.findByName(name);
		if (result == null) {
			return null;
		}
		return result;
	}

	// CREATE product
	@PostMapping("/addProduct")
	public ResponseEntity<Product> createTutorial(@RequestBody Product product) {
		
		try {
			System.err.println(mp.writeValueAsString(product));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		try {
			Product _product = productRepo
					.save(new Product(product.getId(),product.getName(), product.getPrice(), product.getDescription()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// UPDATE product
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateTutorial(@PathVariable("id") String id, @RequestBody Product product) {
		Optional<Product> productData = productRepo.findById(id);
		Product _product = productData.get();
		if (_product != null) {
			_product.setName(product.getName());
			_product.setDescription(product.getDescription());
			_product.setPrice(product.getPrice());
			return new ResponseEntity<>(productRepo.save(_product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// DELETE product
	@DeleteMapping("/product/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
		try {
			productRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
