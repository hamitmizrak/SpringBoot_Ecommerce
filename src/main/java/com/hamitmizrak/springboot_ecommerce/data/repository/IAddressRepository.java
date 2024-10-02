package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<AddressEntity,Long>
// JpaRepository<AddressEntity,Long>
// PagingAndSortingRepository<AddressEntity,Long>

// ID
// protected Long addressId;
// private String street;
// private String city;
// private String country;
// private String postalCode;
// private Date systemDate;

@Repository
public interface IAddressRepository extends CrudRepository<AddressEntity,Long> {
    // Delivered Query

    // Posta koduna göre Veri gösterin.
    //AddressEntity findByPostalCode(String postalCode);


    // @Query
}
