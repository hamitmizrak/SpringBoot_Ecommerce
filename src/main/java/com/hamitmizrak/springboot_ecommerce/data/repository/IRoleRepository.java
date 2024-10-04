package com.hamitmizrak.springboot_ecommerce.data.repository;


import com.hamitmizrak.springboot_ecommerce.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {

    // Delivered Query (database query
    // Database Role Name bulmak
    // select * from Roles as rol where rol.role_name
    Optional<com.hamitmizrak.springboot_ecommerce.data.entity.RoleEntity> findByRoleName(String roleName);

    @Query("select rol from Registers reg join reg.roles rol where reg.registerEmail=:emailParam")
    RoleEntity registerEmailFindRole(@Param("emailParam") String email);
} //end interface
