package com.train.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.train.inventory.controller.HomeController;

@SpringBootApplication
@ComponentScan(basePackageClasses = HomeController.class)

public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}