package com.hamitmizrak.springboot_ecommerce.business.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // Getter,Setter,Equals, hashCode
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor //parametresiz constructor
@AllArgsConstructor //parametreli constructor
@Builder
@Log4j2

//Customer(1)  ---- Address(1)
public class CustomerDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // FIELD

    // ID
    private Long customerId;

    // NAME
    @NotEmpty(message = "{customer.name.validation.constraints.NotNull.message}")
    private String customerName;

    // SURNAME
    @NotEmpty(message = "{customer.surname.validation.constraints.NotNull.message}")
    private String customerSurname;

    // TC NUMBER
    @NotEmpty(message = "{customer.tcnumber.validation.constraints.NotNull.message}")
    private String customerTcNumber;

    // VAT NUMBER
    @NotEmpty(message = "{customer.vatnumber.validation.constraints.NotNull.message}")
    private String customerVatNumber;

    // NOTES
    @NotEmpty(message = "{customer.notes.validation.constraints.NotNull.message}")
    @Lob
    @Size(min=10,message = "{customer.notes.least.validation.constraints.NotNull.message}")
    private String customerNotes;

    // GENDER
    @NotEmpty(message = "{customer.gender.validation.constraints.NotNull.message}")
    private String customerGender;

    // EMAIL
    @NotEmpty(message = "{customer.email.validation.constraints.NotNull.message}")
    private String customerEmail;

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
} //end class CustomerDto
