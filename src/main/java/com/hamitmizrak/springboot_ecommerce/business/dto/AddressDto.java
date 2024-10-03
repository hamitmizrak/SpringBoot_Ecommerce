package com.hamitmizrak.springboot_ecommerce.business.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
