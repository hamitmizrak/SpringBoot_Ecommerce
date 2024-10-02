package com.hamitmizrak.springboot_ecommerce.business.services;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;

import java.util.List;


// D: Dto
// E: Entity
public interface ICustomerServices <D,E> {

    // Model Mapper
    public D entityToDto(E entity);
    public E dtoToEntity(D dto);

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
