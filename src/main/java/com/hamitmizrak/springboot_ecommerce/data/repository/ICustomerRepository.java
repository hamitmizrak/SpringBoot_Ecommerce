package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<CustomerEntity,Long>
// JpaRepository<CustomerEntity,Long>
// PagingAndSortingRepository<CustomerEntity,Long>

@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity,Long> {
    // Delivered Query

    // @Query
}
