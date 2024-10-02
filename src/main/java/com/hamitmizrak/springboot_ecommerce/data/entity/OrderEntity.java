package com.hamitmizrak.springboot_ecommerce.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

/*
Customer  ------  Address
   1    @OneToOne    1

Customer  ------<    Order
   1     @ManyToOne    *

Order  >------<  Product
   *  @ManyToMany    *
 */

// LOMBOK
@Data // @Getter @Setter @ToString @EqualsAndHashCode
@Builder
@Log4j2
@NoArgsConstructor
@AllArgsConstructor

// ENTITY
@Entity(name = "Orders")  // Sql JOIN için yazdım
@Table(name = "order")

//  Order(N) - CustomerEntity(1)
public class OrderEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // RELATION
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="customer_id",nullable = false)
    private CustomerEntity customerEntity;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false, insertable = true, updatable = false)
    protected Long orderId;

    // RELATION

    // ORDER NAME
    @Column(name = "order_name")
    private String orderName;

    // ORDER PRICE
    @Column(name = "order_price")
    private String orderPrice;


    // ORDER CODE
    @Column(name = "order_code")
    private String orderCode;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate;
} //end class CustomerDto
