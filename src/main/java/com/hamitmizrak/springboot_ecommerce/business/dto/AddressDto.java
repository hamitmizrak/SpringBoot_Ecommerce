package com.hamitmizrak.springboot_ecommerce.business.dto;

import com.hamitmizrak.springboot_ecommerce.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
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

// Address(1) ----  Customer(1)
public class AddressDto extends AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // FIELD
    private Long id;

    // STREET
    @NotEmpty(message = "{address.street.validation.constraints.NotNull.message}")
    private String street;

    // CITY
    @NotEmpty(message = "{address.city.validation.constraints.NotNull.message}")
    private String city;

    // COUNTRY
    @NotEmpty(message = "{address.country.validation.constraints.NotNull.message}")
    private String country;

    // POSTA KODU
    @NotEmpty(message = "{address.postal_codes.validation.constraints.NotNull.message}")
    private String postalCode;

    // DATE
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis());
} //end class AddressDto
