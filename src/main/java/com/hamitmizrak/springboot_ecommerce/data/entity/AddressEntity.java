package com.hamitmizrak.springboot_ecommerce.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {

    private String street;
    private String city;
    private String state;
    private String postalCode;
}
