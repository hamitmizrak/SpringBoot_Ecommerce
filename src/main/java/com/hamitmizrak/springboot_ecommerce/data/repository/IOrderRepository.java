package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<AddressEntity,Long>
// JpaRepository<AddressEntity,Long>
// PagingAndSortingRepository<AddressEntity,Long>

@Repository
public interface IOrderRepository extends CrudRepository<OrderEntity,Long> {
    // Delivered Query

    // @Query
}
