package com.hamitmizrak.springboot_ecommerce.business.services;

import java.util.List;


// D: Dto
// E: Entity
public interface IOrderServices<D,E> {

    // Model Mapper
    public D entityToDto(E entity);
    public E dtoToEntity(D dto);

    /////////////////////////////////////////////////////
    // C R U D
    // CREATE
    public D orderServiceCreate(D d);

    // LIST
    public List<D>  orderServiceList();

    // FIND BY ID
    public D  orderServiceFindById(Long id);

    // UPDATE
    public D  orderServiceUpdateFindById(Long id, D d);

    // DELETE
    public D  orderServiceDeleteFindById(Long id);

}
