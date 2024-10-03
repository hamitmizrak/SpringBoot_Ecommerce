package com.hamitmizrak.springboot_ecommerce.business.services;
import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;

import java.util.List;


// D: Dto
// E: Entity
public interface ICustomerServices <D,E> {

    // Model Mapper

    public CustomerEntity  customerDtoToEntity(CustomerDto customerDto);
    public CustomerDto customerEntityToDto(CustomerEntity customerEntity);

    public OrderEntity orderDtoToEntity(OrderDto orderDto);
    public OrderDto orderEntityToDto(OrderEntity orderEntity);

    public AddressEntity addressDtoToEntity(AddressDto addressDto);
    public AddressDto addressEntityToDto(AddressEntity addressEntity);

    /////////////////////////////////////////////////////
    // C R U D
    // CREATE
    public D customerServiceCreate(D d);

    // LIST
    public List<D>  customerServiceList();

    // FIND BY ID
    public D  customerServiceFindById(Long id);

    // UPDATE
    public D  customerServiceUpdateFindById(Long id, D d);

    // DELETE
    public D  customerServiceDeleteFindById(Long id);


}
