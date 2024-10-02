package com.hamitmizrak.springboot_ecommerce.business.services;
import java.util.List;


// D: Dto
// E: Entity
public interface IAddressServices<D,E> {

    // Model Mapper
    public D entityToDto(E entity);
    public E dtoToEntity(D dto);

    /////////////////////////////////////////////////////
    // C R U D
    // CREATE
    public D addressServiceCreate(D d);

    // LIST
    public List<D>  addressServiceList();

    // FIND BY ID
    public D addressServiceFindById(Long id);

    // UPDATE
    public D  addressServiceUpdateFindById(Long id, D d);

    // DELETE
    public D  addressServiceDeleteFindById(Long id);
}
