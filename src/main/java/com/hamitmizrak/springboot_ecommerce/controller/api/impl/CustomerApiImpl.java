package com.hamitmizrak.springboot_ecommerce.controller.api.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.services.ICustomerServices;
import com.hamitmizrak.springboot_ecommerce.controller.api.ICustomerApi;
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
@RequestMapping("/customer/api/v1")
public class CustomerApiImpl implements ICustomerApi<CustomerDto> {

    // INJECTION
    private final ICustomerServices iCustomerServices;

    // CREATE
    // http://localhost:4444/customer/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> customerApiCreate(@Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(iCustomerServices.customerServiceCreate(customerDto),HttpStatus.OK);
    }

    // LIST
    // http://localhost:4444/customer/api/v1/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> customerApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerServices.customerServiceList());
    }

    // FIND BY ID
    // http://localhost:4444/customer/api/v1/find/1
    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> customerApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iCustomerServices.customerServiceFindById(id));
    }

    // UPDATE BY ID
    // http://localhost:4444/customer/api/v1/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> customerApiUpdateById(@PathVariable(name = "id") Long id, @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(iCustomerServices.customerServiceUpdateFindById(id, customerDto));
    }

    // DELETE BY ID
    // http://localhost:4444/customer/api/v1/delete/1
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> customerApiDeleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iCustomerServices.customerServiceDeleteFindById(id));
    } //end

} //end CustomerApiImpl
