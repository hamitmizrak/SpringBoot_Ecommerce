package com.hamitmizrak.springboot_ecommerce.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */

    private String street;
    private String city;
    private String state;
    private String postalCode;

    // Getters and Setters
}
