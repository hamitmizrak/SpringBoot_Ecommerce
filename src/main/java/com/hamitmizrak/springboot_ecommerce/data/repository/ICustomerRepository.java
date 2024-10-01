package com.hamitmizrak.springboot_ecommerce.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// CrudRepository
// JpaRepository
// PagingAndSortingRepository

@Repository
public interface ICustomerRepository extends CrudRepository {
    // Delivered Query
}
