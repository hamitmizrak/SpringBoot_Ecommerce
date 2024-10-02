package com.hamitmizrak.springboot_ecommerce.controller.api.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.controller.api.ICustomerApi;
import org.springframework.http.ResponseEntity;

import java.util.List;

// Api: Dış dünya ile bağlantı kuracağım yer
public class CustomerApiImpl implements ICustomerApi<CustomerDto> {

    // INJECTION


    @Override
    public ResponseEntity<?> customerApiCreate(CustomerDto customerDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> customerApiList() {
        return null;
    }

    @Override
    public ResponseEntity<?> customerApiFindById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> customerApiUpdateById(Long id, CustomerDto customerDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> customerApiDeleteById(Long id) {
        return null;
    }
}
