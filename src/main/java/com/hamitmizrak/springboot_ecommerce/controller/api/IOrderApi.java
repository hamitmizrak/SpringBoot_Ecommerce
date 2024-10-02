package com.hamitmizrak.springboot_ecommerce.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IOrderApi<D> {

    // C R U D
    // CREATE
    public ResponseEntity<?> orderApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> orderApiList();

    // FIND BY ID
    public ResponseEntity<?> orderApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?>orderApiUpdateById(Long id, D d);

    // DELETE
    public ResponseEntity<?> orderApiDeleteById(Long id);
} //end IorderApi
