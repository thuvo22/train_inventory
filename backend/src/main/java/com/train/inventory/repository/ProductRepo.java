package com.train.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.train.inventory.entity.Product;
public interface ProductRepo extends MongoRepository<Product, String> {
	List<Product> findByName(String name);

	List<Product> findByPrice(double price);
		
	Optional<Product> findById(String id);
}