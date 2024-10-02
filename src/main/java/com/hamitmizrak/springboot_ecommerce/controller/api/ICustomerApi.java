package com.hamitmizrak.springboot_ecommerce.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface ICustomerApi<D> {

    // C R U D
    // CREATE
    public ResponseEntity<?> customerApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> customerApiList();

    // FIND BY ID
    public ResponseEntity<?> customerApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> customerApiUpdateById(Long id, D d);

    // DELETE
    public ResponseEntity<?> customerApiDeleteById(Long id);
} //end ICustomerApi
