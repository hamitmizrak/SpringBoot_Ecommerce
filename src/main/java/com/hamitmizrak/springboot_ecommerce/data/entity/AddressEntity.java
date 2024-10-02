package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.data.embedded.EmbeddableCustomerEntity;
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
@Entity(name = "Address")  // Sql JOIN için yazdım
@Table(name = "adress")

// Addres(1) -  CustomerEntity(1)
public class AddressEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true, nullable = false, insertable = true, updatable = false)
    protected Long addressId;

    // RELATION

    // STREET
    @Column(name = "street")
    private String street;

    // CITY
    private String city;

    // COUNTRY
    private String country;

    // POSTA CODE
    @Column(name = "postal_code")
    private String postalCode;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate;
} //end class CustomerDto
