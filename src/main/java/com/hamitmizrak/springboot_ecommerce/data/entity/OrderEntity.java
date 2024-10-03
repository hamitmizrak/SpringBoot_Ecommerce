package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.annotation.UniqueOrderNumber;
import com.hamitmizrak.springboot_ecommerce.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter

// ENTITY
@Entity
@Table(name = "orders")
public class OrderEntity  extends  AuditingAwareBaseEntity  implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;


    //@UniqueOrderNumber
    private String number;

    @Column(name="total_amount")
    private Double totalAmount;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate;

    // RELATION
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;
}