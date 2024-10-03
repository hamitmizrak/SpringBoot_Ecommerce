package com.hamitmizrak.springboot_ecommerce.business.dto;

import com.hamitmizrak.springboot_ecommerce.audit.AuditingAwareBaseDto;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2

//Customer(1)  ---- Address(1)
//Customer(1)  ---- Order(N)
public class CustomerDto extends AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;


    private Long id;

    // Embedded
    private PersonalInfoDto personalInfo;

    // Relation
    // Customer(1) - Address(1)
    private AddressDto address;

    // Customer(1)- Order(N)
    private List<OrderDto> orders = new ArrayList<>();
}
