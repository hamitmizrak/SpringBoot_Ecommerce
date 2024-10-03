package com.hamitmizrak.springboot_ecommerce.business.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;

    // Embedded
    private PersonalInfoDto personalInfo;

    // Relation
    // Customer(1) - Address(1)
    private AddressDto address;

    // Customer(1)- Order(N)
    private List<OrderDto> orders = new ArrayList<>();
}
