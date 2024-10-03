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
@Getter
@Setter

// ENTITY
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
}