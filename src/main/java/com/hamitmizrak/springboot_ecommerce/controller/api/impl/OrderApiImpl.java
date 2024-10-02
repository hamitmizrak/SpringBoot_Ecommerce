package com.hamitmizrak.springboot_ecommerce.controller.api.impl;


import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IOrderServices;
import com.hamitmizrak.springboot_ecommerce.controller.api.IOrderApi;
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
@RequestMapping("/order/api/v1")
public class OrderApiImpl implements IOrderApi<OrderDto> {

    // INJECTION
    private final IOrderServices iOrderServices;

    // CREATE
    // http://localhost:4444/order/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> orderApiCreate(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(iOrderServices.orderServiceCreate(orderDto),HttpStatus.OK);
    }

    // LIST
    // http://localhost:4444/order/api/v1/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<OrderDto>> orderApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iOrderServices.orderServiceList());
    }

    // FIND BY ID
    // http://localhost:4444/order/api/v1/find/1
    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> orderApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iOrderServices.orderServiceFindById(id));
    }

    // UPDATE BY ID
    // http://localhost:4444/order/api/v1/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> orderApiUpdateById(@PathVariable(name = "id") Long id, @Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(iOrderServices.orderServiceUpdateFindById(id, orderDto));
    }

    // DELETE BY ID
    // http://localhost:4444/order/api/v1/delete/1
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> orderApiDeleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iOrderServices.orderServiceDeleteFindById(id));
    } //end

} //end AddressApiImpl
