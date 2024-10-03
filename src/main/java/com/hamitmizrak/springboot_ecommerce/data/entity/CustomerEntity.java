package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.data.embedded.PersonalInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/*
Customer  ------  Address
   1    @OneToOne    1

Customer  ------<    Order
   1     @ManyToOne    *

Order  >------<  Product
   *  @ManyToMany    *
*/

// LOMBOK
@Setter
@Getter

// ENTITY
@Entity
@Table(name = "customers")

// Customer(1) - Order(N)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Gömülü personal info
    @Embedded
    private PersonalInfo personalInfo;

    // Address ile One-to-One ilişki
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    // Order ile One-to-Many ilişki
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();
}
