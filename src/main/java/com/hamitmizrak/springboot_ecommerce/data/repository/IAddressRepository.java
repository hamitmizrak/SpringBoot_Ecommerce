package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<AddressEntity,Long>
// JpaRepository<AddressEntity,Long>
// PagingAndSortingRepository<AddressEntity,Long>

@Repository
public interface IAddressRepository extends CrudRepository<AddressEntity,Long> {
    // Delivered Query

    // @Query
}
