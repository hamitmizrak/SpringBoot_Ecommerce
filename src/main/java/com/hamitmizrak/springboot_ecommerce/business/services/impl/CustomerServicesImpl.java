package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.ModelMapperBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.services.ICustomerServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Asıl İş katmanımız
@Service
public class CustomerServicesImpl implements ICustomerServices<CustomerDto, CustomerEntity> {
    // Field
    // Lombok Constructor Parametreli Injection
    private final ICustomerRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;

    // Model Mapper
    @Override
    public CustomerDto entityToDto(CustomerEntity entity) {
        return modelMapperBean.getModelMapperBeanMethod().map(entity, CustomerDto.class);
    }

    @Override
    public CustomerEntity dtoToEntity(CustomerDto dto) {
        return modelMapperBean.getModelMapperBeanMethod().map(dto, CustomerEntity.class);
    }

    ///////////////////////////////////////////////////////////////////
    // CREATE
    @Transactional
    @Override
    public CustomerDto customerServiceCreate(CustomerDto customerDto) {
        if(customerDto!=null){
            CustomerEntity customerEntity =dtoToEntity(customerDto);
            iCustomerRepository.save(customerEntity);
            // customerDto.setCustomerId(customerEntity.getCustomerId());
            // customerDto.setCreatedDate(customerEntity.getCreatedDate());
        }else{
            throw new NullPointerException("CustomerDto is null");
        }
        return customerDto;
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
    @Transactional
    @Override
    public CustomerDto customerServiceUpdateFindById(Long id, CustomerDto customerDto) {
        return null;
    }

    // DELETE
    @Transactional
    @Override
    public CustomerDto customerServiceDeleteFindById(Long id) {
        return null;
    }
} //end  CustomerServicesImpl
