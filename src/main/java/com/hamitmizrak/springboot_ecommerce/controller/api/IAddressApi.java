package com.hamitmizrak.springboot_ecommerce.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IAddressApi<D> {

    // C R U D
    // CREATE
    public ResponseEntity<?> addressApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> addressApiList();

    // FIND BY ID
    public ResponseEntity<?> addressApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?>addressApiUpdateById(Long id, D d);

    // DELETE
    public ResponseEntity<?> addressApiDeleteById(Long id);
} //end IAddressApi
