package com.lundu.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lundu.billingservice.entities.data.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Product product;
    private Long productID;

    private double price;
    private double quantity;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;


}
