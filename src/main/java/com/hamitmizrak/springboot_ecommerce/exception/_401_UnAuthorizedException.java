package com.hamitmizrak.springboot_ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Status Code : 401
// UNAUTHORIZED : Login Oldun mu ?

@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
public class _401_UnAuthorizedException extends RuntimeException {

    // Constructor Parametreli
    public _401_UnAuthorizedException(String message) {
        super(message);
    } //end method
} //end class
