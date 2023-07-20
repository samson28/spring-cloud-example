package com.lundu.billingservice.service.client;

import com.lundu.billingservice.entities.data.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @GetMapping("/products/{id}?projection=fullProduct")
    Product findProductById(@PathVariable(name = "id") Long id);

    @GetMapping("/products?projection=fullProduct")
    PagedModel<Product> findAll();

}
