package com.lundu.billingservice.controller;

import com.lundu.billingservice.entities.Bill;
import com.lundu.billingservice.repositories.BillRepository;
import com.lundu.billingservice.repositories.ProductItemRepository;
import com.lundu.billingservice.service.client.CustomerServiceClient;
import com.lundu.billingservice.service.client.InventoryServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;

    @GetMapping("/bills/full/{id}")
    Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(bill.getId()));
        bill.getProductItems().forEach(pi ->{
            pi.setProduct(inventoryServiceClient.findProductById(pi.getProductID()));
        });
        return bill;
    }
}
