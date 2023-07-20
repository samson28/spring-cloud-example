package com.lundu.customerservice;

import com.lundu.customerservice.entities.Customer;
import com.lundu.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.RestControllerConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			Stream.of("TECH","HEALTH","SPACE").forEach(name -> {
				customerRepository.save(new Customer(null,name,name+"@gmail.com"));
			});
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
