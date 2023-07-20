package com.lundu.billingservice.entities.data;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
}
