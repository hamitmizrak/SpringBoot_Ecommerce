package com.hamitmizrak.springboot_ecommerce.business.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private String orderNumber;
    private Double totalAmount;
}
