package com.hamitmizrak.springboot_ecommerce.data.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// @Embeddable
@Embeddable
public class PersonalInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String tcNumber;
}