package com.lundu.billingservice.entities;

import com.lundu.billingservice.entities.data.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;

    @Transient @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;

    @Transient
    private Customer customer;
    private Long customerID;
}
