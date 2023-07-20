package com.lundu.billingservice;

import com.lundu.billingservice.entities.Bill;
import com.lundu.billingservice.entities.ProductItem;
import com.lundu.billingservice.entities.data.Customer;
import com.lundu.billingservice.repositories.BillRepository;
import com.lundu.billingservice.repositories.ProductItemRepository;
import com.lundu.billingservice.service.client.CustomerServiceClient;
import com.lundu.billingservice.service.client.InventoryServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			BillRepository billRepository, 	ProductItemRepository productItemRepository,
			CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient,
			RepositoryRestConfiguration repositoryRestConfiguration
	){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Bill.class);
			Bill bill = new Bill();
			bill.setBillingDate(new Date());
			Customer customer = customerServiceClient.findCustomerById(1L);
			bill.setCustomerID(customer.getId());
			billRepository.save(bill);
			inventoryServiceClient.findAll().getContent().forEach(p -> {
				productItemRepository.save(new ProductItem(null,null,p.getId(),p.getPrice(),(int)(1+Math.random()*1000),bill));
			});
			billRepository.findAll().forEach(System.out::println);
		};
	}

}
