package com.train.inventory.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="product")
public class Product {
	@Id
	private String id;

	private String name;

	private double price;

	private String description;

	
	
	//Getter and setter
	
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	//constructors
	public Product() {
	}

	public Product(String id,String name, double price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}
	// toString() method

	@Override
	public String toString() {
		return "Product{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", price=" + price + ", description='"
				+ description + '\'' + '}';
	}
	
	
}
