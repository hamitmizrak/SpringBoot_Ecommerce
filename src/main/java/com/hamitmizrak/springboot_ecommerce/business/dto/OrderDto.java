package com.hamitmizrak.springboot_ecommerce.business.dto;


import com.hamitmizrak.springboot_ecommerce.annotation.UniqueOrderNumber;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long id;

    @UniqueOrderNumber
    private String orderNumber;

    private Double totalAmount;
}
