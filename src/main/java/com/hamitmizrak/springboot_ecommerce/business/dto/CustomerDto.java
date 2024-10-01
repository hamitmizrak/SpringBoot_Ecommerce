package com.hamitmizrak.springboot_ecommerce.business.dto;

/*
id (primary key)
name
surname
tcNumber
vatNumber (Vergi Numarası)
customerNotes
dateOfBirth
gender
email
*/

import lombok.*;

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
public class CustomerDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // Field

    // ID
    private Long customerId;

    // NAME
    private String customerName;

    // SURNAME
    private String customerSurname;

    // TC NUMBER
    private String customerTcNumber;

    // VAT NUMBER
    private String vatNumber;

    // NOTES
    private String customerNotes;

    // GENDER
    private String gender;

    // EMAIL
    private String email;

    // DATE
    @Builder.Default
    private Date createdDate=new Date(System.currentTimeMillis());
}
