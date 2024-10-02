package com.hamitmizrak.springboot_ecommerce.data.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class PersonalInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String tcNumber;

    // Getters and Setters
}