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

// Order(N) ----  Customer(1)
public class OrderDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // FIELD

    // ID
    private Long orderId;

    // ORDER NAME
    @NotEmpty(message = "{order.name.validation.constraints.NotNull.message}")
    private String orderName;

    // ORDER PRICE
    @NotEmpty(message = "{order.price.validation.constraints.NotNull.message}")
    private String orderPrice;

    // ORDER CODE
    @NotEmpty(message = "{order.code.validation.constraints.NotNull.message}")
    private String orderCode;

    // DATE
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis());

} //end class AddressDto
