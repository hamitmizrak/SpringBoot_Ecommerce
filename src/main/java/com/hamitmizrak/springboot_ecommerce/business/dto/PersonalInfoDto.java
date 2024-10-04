package com.hamitmizrak.springboot_ecommerce.business.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalInfoDto {
    private String firstName;
    private String lastName;
    private String email;
    private String tcNumber;
}
