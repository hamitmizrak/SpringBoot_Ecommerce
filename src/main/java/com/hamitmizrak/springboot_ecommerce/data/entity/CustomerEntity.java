package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.audit.AuditingAwareBaseEntity;
import com.hamitmizrak.springboot_ecommerce.data.embedded.EmbeddableCustomer;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Getter
@Setter


// ENTITY
@Entity(name = "Customers")  // Sql JOIN için yazdım
@Table(name = "customer")

// CustomerEntity(1) - Addres(1)
// CustomerEntity(1) - Order(N)
public class CustomerEntity extends  AuditingAwareBaseEntity  implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;

    // Gömülü personal info
    @Embedded
    private EmbeddableCustomer embeddableCustomer;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate;

    // Address ile One-to-One ilişki
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity addressEntity;

    // Order ile One-to-Many ilişki
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();
}
