package com.hamitmizrak.springboot_ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Status Code      : 402
// PAYMENT_REQUIRED : Para Ödendi mi?

@ResponseStatus(value= HttpStatus.PAYMENT_REQUIRED)
public class _402_PaymentRequiredException extends RuntimeException {

    // Constructor Parametreli
    public _402_PaymentRequiredException(String message) {
        super(message);
    } //end method
} //end class
