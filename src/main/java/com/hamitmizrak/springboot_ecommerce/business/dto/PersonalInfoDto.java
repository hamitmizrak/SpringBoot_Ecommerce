package com.hamitmizrak.springboot_ecommerce.business.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class PersonalInfoDto  implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // Embedded
    // NAME
    @NotEmpty(message = "{customer.name.validation.constraints.NotNull.message}")
    private String name;

    // SURNAME
    @NotEmpty(message = "{customer.surname.validation.constraints.NotNull.message}")
    private String surname;

    // TC NUMBER
    @NotEmpty(message = "{customer.tcnumber.validation.constraints.NotNull.message}")
    private String tcNumber ;

    // VAT NUMBER
    @NotEmpty(message = "{customer.vatnumber.validation.constraints.NotNull.message}")
    private String vatNumber ;

    // NOTES
    @NotEmpty(message = "{customer.notes.validation.constraints.NotNull.message}")
    @Lob
    @Size(min=4,message = "{customer.notes.least.validation.constraints.NotNull.message}")
    private String notes;

    // EMAIL
    @NotEmpty(message = "{customer.email.validation.constraints.NotNull.message}")
    private String email;

    // DATE
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis());

    /* PSVM
    public static void main(String[] args) {
        CustomerDto customerDto= CustomerDto.builder()
                .customerId(1L)
                .customerName("Name")
                .customerTcNumber("TcNumber")
                .build();
    }
     */
}
