package com.hamitmizrak.springboot_ecommerce.business.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
