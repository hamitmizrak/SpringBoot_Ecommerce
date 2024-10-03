package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.audit.AuditingAwareBaseEntity;
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
@NoArgsConstructor
@AllArgsConstructor

// ENTITY
@Entity(name = "Address")  // Sql JOIN için yazdım
@Table(name = "adress")

//  Addres(1) - CustomerEntity(1)
public class AddressEntity extends BaseEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // STREET
    @Column(name = "street")
    private String street;

    // STREET
    @Column(name = "city")
    private String city;

    // COUNTRY
    @Column(name = "country")
    private String country;

    // POSTA CODE
    @Column(name = "postal_code")
    private String postalCode;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate;
} //end class CustomerDto
