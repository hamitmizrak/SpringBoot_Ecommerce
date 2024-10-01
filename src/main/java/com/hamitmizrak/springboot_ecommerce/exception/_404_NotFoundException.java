package com.hamitmizrak.springboot_ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Status Code      : 404
// NOT_FOUND        : Sayfa Bulunamadı

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class _404_NotFoundException extends RuntimeException {

    // Constructor Parametreli
    public _404_NotFoundException(String message) {
        super(message);
    } //end method
} //end class
