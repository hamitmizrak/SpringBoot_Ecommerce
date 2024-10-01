package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.services.ICustomerServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Asıl İş katmanımız
@Service
public class CustomerServicesImpl implements ICustomerServices<CustomerDto, CustomerEntity> {
    // Field

    // Model Mapper
    @Override
    public CustomerDto entityToDto(CustomerEntity entity) {
        return null;
    }

    @Override
    public CustomerEntity dtoToEntity(CustomerDto dto) {
        return null;
    }

    ///////////////////////////////////////////////////////////////////
    // CREATE
    @Override
    public CustomerDto customerServiceCreate(CustomerDto customerDto) {
        return null;
    }

    // LIST
    @Override
    public List<CustomerDto> customerServiceList() {
        return List.of();
    }

    // FIND
    @Override
    public CustomerDto customerServiceFindById(Long id) {
        return null;
    }

    // UPDATE
    @Override
    public CustomerDto customerServiceUpdateFindById(Long id, CustomerDto customerDto) {
        return null;
    }

    // DELETE
    @Override
    public CustomerDto customerServiceDeleteFindById(Long id) {
        return null;
    }
} //end  CustomerServicesImpl
