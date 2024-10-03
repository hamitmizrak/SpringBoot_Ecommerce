package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<OrderEntity, Long>
// JpaRepository<OrderEntity, Long>
// PagingAndSortingRepository<OrderEntity, Long>

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
