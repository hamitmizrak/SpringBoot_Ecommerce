package com.hamitmizrak.springboot_ecommerce.data.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// Özel Kolonları buraya ekle
@Embeddable
public class EmbeddableCustomerEntity {

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
    @Column(name = "customer_email", columnDefinition = "varchar(255) default 'example@gmail.com'")
    private String email;

} //end class EmbeddableCustomerEntity
