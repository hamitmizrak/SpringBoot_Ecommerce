package com.hamitmizrak.springboot_ecommerce.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    // NAME
    @Column(name = "customer_name")
    private String customerName;

    // SURNAME
    @Column(name = "customer_surname")
    private String customerSurname;

    // TC NUMBER
    @Column(name = "customer_tc_number")
    private String customerTcNumber;

    // VAT NUMBER
    @Column(name = "customer_vat_number")
    private String customerVatNumber;

    // NOTES
    @Column(name = "customer_notes")
    private String customerNotes;

    // GENDER
    private String gender;

    // EMAIL
    private String email;

    // DATE
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) // yıl ay gün
    //@Temporal(TemporalType.TIME) // saat dakika saniye
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date createdDate = new Date(System.currentTimeMillis());

} //end class CustomerDto
