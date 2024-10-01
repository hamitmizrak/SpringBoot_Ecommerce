package com.hamitmizrak.springboot_ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Status Code           : 500
// INTERNAL_SERVER_ERROR : Server Hatası

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class _500_IntervalServerException extends RuntimeException {

    // Constructor Parametreli
    public _500_IntervalServerException(String message) {
        super(message);
    } //end method
} //end class
