package com.lundu.inventoryservice;

import com.lundu.inventoryservice.entities.Product;
import com.lundu.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Product.class);
			Stream.of("PC","TV","PHONE").forEach(name -> {
				productRepository.save(new Product(null,name,100*Math.random()*100));
			});
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
