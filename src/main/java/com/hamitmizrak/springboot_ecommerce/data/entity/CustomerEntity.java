package com.hamitmizrak.springboot_ecommerce.data.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // Getter,Setter,Equals, hashCode
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor //parametresiz constructor
@AllArgsConstructor //parametresiz constructor
@Builder
@Log4j2

//Customer(1)  ---- Address(1)
@Entity(name="Customers")
@Table(name="customer")
public class CustomerEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // Field

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",unique = true,nullable = false,insertable = true,updatable = false)
    private Long customerId;

    // Embedded

    // DATE
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) // yıl ay gün
    //@Temporal(TemporalType.TIME) // saat dakika saniye
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date createdDate = new Date(System.currentTimeMillis());

} //end class CustomerDto
