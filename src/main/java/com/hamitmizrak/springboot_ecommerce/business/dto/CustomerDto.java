package com.hamitmizrak.springboot_ecommerce.business.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CustomerDto {

    private Long id;
    private PersonalInfoDto personalInfo; // Embedded

    private AddressDto address;
    private List<OrderDto> orders = new ArrayList<>();

    // Getters and Setters
}
