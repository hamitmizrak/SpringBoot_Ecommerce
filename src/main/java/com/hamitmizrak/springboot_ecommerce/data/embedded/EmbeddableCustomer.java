package com.hamitmizrak.springboot_ecommerce.data.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// LOMBOK
@Getter
@Setter

// @Embeddable
@Embeddable
public class EmbeddableCustomer {

    private String name;
    private String surname;
    private String tcNumber;
    private String vatNumber;
    private String notes;
    private String email;
}
