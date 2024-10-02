package com.hamitmizrak.springboot_ecommerce.business.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class AddressDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // FIELD

    // ID
    private Long adressId;

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