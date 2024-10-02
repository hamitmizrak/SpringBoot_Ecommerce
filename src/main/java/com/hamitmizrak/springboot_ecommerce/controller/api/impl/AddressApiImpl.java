package com.hamitmizrak.springboot_ecommerce.controller.api.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IAddressServices;
import com.hamitmizrak.springboot_ecommerce.controller.api.IAddressApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Api: Dış dünya ile bağlantı kuracağım yer
@RestController
@CrossOrigin
@RequestMapping("/address/api/v1")
public class AddressApiImpl implements IAddressApi<AddressDto> {

    // INJECTION
    private final IAddressServices iAddressServices;

    // CREATE
    // http://localhost:4444/address/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> addressApiCreate(@Valid @RequestBody AddressDto addressDto) {
        return new ResponseEntity<>(iAddressServices.addressServiceCreate(addressDto),HttpStatus.OK);
    }

    // LIST
    // http://localhost:4444/address/api/v1/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<AddressDto>> addressApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iAddressServices.addressServiceList());
    }

    // FIND BY ID
    // http://localhost:4444/address/api/v1/find/1
    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> addressApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iAddressServices.addressServiceFindById(id));
    }

    // UPDATE BY ID
    // http://localhost:4444/address/api/v1/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> addressApiUpdateById(@PathVariable(name = "id") Long id, @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(iAddressServices.addressServiceUpdateFindById(id, addressDto));
    }

    // DELETE BY ID
    // http://localhost:4444/address/api/v1/delete/1
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> addressApiDeleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iAddressServices.addressServiceDeleteFindById(id));
    } //end

} //end AddressApiImpl
