package com.hamitmizrak.springboot_ecommerce.business.dto;

import jakarta.persistence.Column;
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

// Order(N) ----  Customer(1)
public class OrderDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // ID
    private Long id;

    // ORDER NAME
    @NotEmpty(message = "{order.name.validation.constraints.NotNull.message}")
    private String name;

    // ORDER PRICE
    @NotEmpty(message = "{order.price.validation.constraints.NotNull.message}")
    private String price;

    //@UniqueOrderNumber
    @NotEmpty(message = "{order.code.validation.constraints.NotNull.message}")
    private String number;

    @NotEmpty(message = "{order.code.validation.constraints.NotNull.message}")
    private Double totalAmount;

    // DATE
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis());
}