package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.data.embedded.PersonalInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
/*
Customer  ------  Address
   1    @OneToOne    1

Customer  ------<    Order
   1     @ManyToOne    *

Order  >------<  Product
   *  @ManyToMany    *
*/
@Entity
@Table(name = "customers")
public class CustomerEntity {

    // H2DB,Postgresql: @GeneratedValue(strategy = GenerationType.IDENTITY)
    // MYSQL: @GeneratedValue(strategy = GenerationType.AUTO)
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

    // Getters and Setters
}
