package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.data.embedded.EmbeddableCustomerEntity;
import jakarta.persistence.*;
import lombok.*;
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
//@NoArgsConstructor
@AllArgsConstructor

// ENTITY
@Entity(name = "Customers")  // Sql JOIN için yazdım
@Table(name = "customer")

// CustomerEntity(1) - Addres(1)
// CustomerEntity(1) - Order(N)
public class CustomerEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false, insertable = true, updatable = false)
    protected Long customerId;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate;

    // RELATION
    // Address ID Bilgisini Customer içinde saklıyorum.
    @OneToOne(targetEntity =AddressEntity.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="address_id")
    private AddressEntity addressEntity;

    // Field
    // Embedded
    @Embedded
    private EmbeddableCustomerEntity embeddableCustomerEntity = new EmbeddableCustomerEntity();

    public CustomerEntity() {
    }

    public CustomerEntity(EmbeddableCustomerEntity embeddableCustomerEntity, AddressEntity addressEntity) {
        this.embeddableCustomerEntity = embeddableCustomerEntity;
        this.addressEntity = addressEntity;
    }
} //end class CustomerDto
